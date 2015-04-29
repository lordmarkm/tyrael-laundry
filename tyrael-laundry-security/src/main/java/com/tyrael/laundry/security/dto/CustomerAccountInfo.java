package com.tyrael.laundry.security.dto;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
public class CustomerAccountInfo extends BaseTyraelDto {

    private CustomerInfo customerInfo;
    private AccountInfo accountInfo;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("customerInfo", customerInfo)
            .append("accountInfo", accountInfo);
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

}
