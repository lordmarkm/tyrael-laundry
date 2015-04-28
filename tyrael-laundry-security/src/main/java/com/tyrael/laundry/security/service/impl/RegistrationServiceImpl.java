package com.tyrael.laundry.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldy.commons.security.models.Account;
import com.baldy.commons.security.services.AccountService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.security.dto.RegistrationForm;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.model.JobOrderRegistrationCode;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.security.service.JobOrderRegistrationCodeService;
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

    @Autowired
    private JobOrderRegistrationCodeService jobOrderRegistrationCodeService;

    @Override
    public void register(RegistrationForm regForm) {
        JobOrderRegistrationCode regCode =
                jobOrderRegistrationCodeService.findByJobOrder_TrackingNo(regForm.getJobOrderTrackingNo());
        if (null == regCode || !regCode.getRegistrationCode().equals(regForm.getRegistrationCode())) {
            throw new IllegalArgumentException("Incorrect registration code");
        }

        Account existing = accountService.findByUsername(regForm.getUsername());
        if (null != existing) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        Account account = new Account();
        account.setUsername(regForm.getUsername());
        account.setPassword(regForm.getPassword());
        account.setAuthorities("ROLE_CUSTOMER");
        account = accountService.save(account);

        JobOrder jobOrder = jobOrderService.findByTrackingNo(regForm.getJobOrderTrackingNo());
        Customer customer = jobOrder.getCustomer();

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customer);
        customerAccount.setAccount(account);
        customerAccountService.save(customerAccount);

        //Delete all reg codes w/ the same owner as the one that was just claimed
        List<JobOrderRegistrationCode> claimedCodes = jobOrderRegistrationCodeService.findByJobOrder_Customer(customer);
        jobOrderRegistrationCodeService.delete(claimedCodes);
    }

}
