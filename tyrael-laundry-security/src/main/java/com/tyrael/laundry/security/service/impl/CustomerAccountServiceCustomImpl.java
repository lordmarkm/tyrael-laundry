package com.tyrael.laundry.security.service.impl;

import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.security.dto.CustomerAccountInfo;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.security.service.custom.CustomerAccountServiceCustom;

/**
 * @author mbmartinez
 */
public class CustomerAccountServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<CustomerAccount, CustomerAccountInfo, CustomerAccountService>
    implements CustomerAccountServiceCustom {

    @Override
    public CustomerAccountInfo findByAccountUsernameInfo(String username) {
        LOG.debug("Finding account by username. username={}", username);
        CustomerAccount customerAccount = repo.findByAccount_Username(username);
        LOG.debug("Found customer account. customer={}, account={}", customerAccount.getCustomer(), customerAccount.getAccount());
        return toDto(customerAccount);
    }

}
