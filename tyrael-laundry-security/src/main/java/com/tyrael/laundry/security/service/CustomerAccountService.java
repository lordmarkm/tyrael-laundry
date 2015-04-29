package com.tyrael.laundry.security.service;

import com.baldy.commons.security.models.Account;
import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.security.model.CustomerAccount;

/**
 * @author mbmartinez
 */
public interface CustomerAccountService extends TyraelJpaService<CustomerAccount>, CustomerAccountServiceCustom {

    CustomerAccount findByCustomer(Customer customer);
    CustomerAccount findByCustomerId(Long id);
    CustomerAccount findByCustomerUsername(String username);
    CustomerAccount findByAccount(Account account);

}
