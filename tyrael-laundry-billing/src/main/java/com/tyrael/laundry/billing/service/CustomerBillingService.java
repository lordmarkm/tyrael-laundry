package com.tyrael.laundry.billing.service;

import com.tyrael.laundry.billing.dto.CustomerBillingInfo;

/**
 * @author mbmartinez
 */
public interface CustomerBillingService {

    CustomerBillingInfo prepareBillingInfo(Long customerId);
    CustomerBillingInfo prepareBillingInfo(String username);

}
