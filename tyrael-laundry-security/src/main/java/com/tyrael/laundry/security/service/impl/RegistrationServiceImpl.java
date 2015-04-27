package com.tyrael.laundry.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldy.commons.security.models.Account;
import com.baldy.commons.security.services.AccountService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.security.service.RegistrationService;
import com.tyrael.laundry.service.JobOrderService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private JobOrderService jobOrderService;

    @Override
    public void register(String jobOrderTrackingNo, String username, String password) {
        Account existing = accountService.findByUsername(username);
        if (null != existing) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setAuthorities("ROLE_CUSTOMER");
        account = accountService.save(account);

        JobOrder jobOrder = jobOrderService.findByTrackingNo(jobOrderTrackingNo);
        Customer customer = jobOrder.getCustomer();
        
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAccount(account);
        customerAccountService.save(customerAccount);
    }

}
