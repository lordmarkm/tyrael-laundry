package com.tyrael.laundry.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldy.commons.security.services.AccountService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.security.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Override
    public void register(String jobOrderTrackingNo, String username, String password) {
        // TODO Auto-generated method stub
        
    }

}
