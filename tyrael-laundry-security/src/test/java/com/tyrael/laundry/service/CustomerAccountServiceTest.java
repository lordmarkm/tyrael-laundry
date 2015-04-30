package com.tyrael.laundry.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
@DatabaseSetup("classpath:testData.xml")
public class CustomerAccountServiceTest {

    @Autowired
    private CustomerAccountService service;

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveWithNoAccountOrCustomer() {
        //CustomerAccount can't have null account or null customer
        CustomerAccount ca = new CustomerAccount();
        service.save(ca);
    }

    @Test
    public void testRetrieves() {
        assertNotNull(service.findOne(1l));
        assertNotNull(service.findByAccount_Username("markm"));
        assertEquals(new Long(1), service.findByAccount_Username("markm").getId());
        //assertNotNull(service.findByAccountUsernameInfo("markm"));
        assertNotNull(service.findByCustomer_Id(1l));
        assertEquals("markm", service.findByCustomer_Id(1l).getAccount().getUsername());
    }

}
