package com.tyrael.laundry.security.dto;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
public class CustomerAccountInfo extends BaseTyraelDto {

    private CustomerInfo customer;
    private AccountInfo account;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("customerInfo", customer)
            .append("accountInfo", account);
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public AccountInfo getAccount() {
        return account;
    }

    public void setAccount(AccountInfo account) {
        this.account = account;
    }


}
