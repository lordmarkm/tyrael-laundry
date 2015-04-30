package com.tyrael.laundry.security.service;

import com.baldy.commons.security.models.Account;
import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.service.custom.CustomerAccountServiceCustom;

/**
 * @author mbmartinez
 */
public interface CustomerAccountService extends TyraelJpaService<CustomerAccount>, CustomerAccountServiceCustom {

    CustomerAccount findByCustomer(Customer customer);
    CustomerAccount findByCustomer_Id(Long id);
    CustomerAccount findByAccount(Account account);
    CustomerAccount findByAccount_Username(String username);

}
