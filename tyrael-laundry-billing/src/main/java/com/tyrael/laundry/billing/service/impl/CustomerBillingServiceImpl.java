package com.tyrael.laundry.billing.service.impl;

import static com.tyrael.laundry.billing.util.BigDecimalUtil.gt;
import static com.tyrael.laundry.billing.util.BigDecimalUtil.lt;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tyrael.laundry.billing.dto.CustomerBillingInfo;
import com.tyrael.laundry.billing.service.CustomerBillingService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.service.CustomerService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
@Service
@Transactional(readOnly = true)
public class CustomerBillingServiceImpl implements CustomerBillingService {

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private Mapper mapper;

    @Override
    public CustomerBillingInfo prepareBillingInfo(Long customerId) {
        Customer customer = customerService.findOne(customerId);
        return prepareBillingInfo(customer);
    }

    @Override
    public CustomerBillingInfo prepareBillingInfo(String username) {
        CustomerAccount customerAccount = customerAccountService.findByAccount_Username(username);
        return prepareBillingInfo(customerAccount.getCustomer());
    }

    private CustomerBillingInfo prepareBillingInfo(Customer customer) {

        List<JobOrder> jobOrders = jobOrderService.findByCustomer(customer);

        int openJobOrders = 0;
        int closedJobOrders = 0;
        BigDecimal balance = BigDecimal.ZERO;
        for (JobOrder jobOrder : jobOrders) {
            switch (jobOrder.getStatus()) {
            case NEW:
            case CLEANED:
            case PAID_CLAIMED:
                openJobOrders++;
                break;
            case CLOSED:
            case CANCELLED:
                closedJobOrders++;
            }

            //balance += (amt due - amt paid)
            if (jobOrder.getTotalAmount().compareTo(jobOrder.getTotalAmountPaid()) != 0) {
                balance = balance.add(jobOrder.getTotalAmount().subtract(jobOrder.getTotalAmountPaid()));
            }
        }

        CustomerBillingInfo billingInfo = new CustomerBillingInfo();
        billingInfo.setCustomer(mapper.map(customer, CustomerInfo.class));
        billingInfo.setOpenJobOrders(openJobOrders);
        billingInfo.setClosedJobOrders(closedJobOrders);
        billingInfo.setBalance(gt(balance, ZERO) ? balance : ZERO);
        billingInfo.setCredit(lt(balance, ZERO) ? balance.negate() : ZERO);

        return billingInfo;
    }

}
