package com.tyrael.laundry.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyrael.laundry.security.config.TyraelLaundrySecurityConfig;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.service.config.TyraelLaundrySecurityTestsConfig;
import com.tyrael.laundry.service.config.TyraelLaundrySecurityTestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    TyraelLaundrySecurityTestsConfig.class,
    TyraelLaundrySecurityTestsPersistenceConfig.class,
    TyraelLaundrySecurityConfig.class
})
public class CustomerAccountServiceTest {

    @Autowired
    private CustomerAccountService caService;

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveWithNoAccountOrCustomer() {
        //CustomerAccount can't have null account or null customer
        CustomerAccount ca = new CustomerAccount();
        caService.save(ca);
    }
}
