package com.tyrael.laundry.security.method;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.service.JobOrderService;

public class CustomTyraelLaundrySecurityExpressionRoot extends BaseTyraelLaundrySecurityExpressionRoot {

    private static Logger LOG = LoggerFactory.getLogger(CustomTyraelLaundrySecurityExpressionRoot.class);

    private JobOrderService jobOrderService;
    private CustomerAccountService customerAccountService;

    public CustomTyraelLaundrySecurityExpressionRoot(Authentication a) {
        super(a);
    }

    /**
     * If the job order is unclaimed, anybody can access it. Otherwise, only the owner can access it.
     */
    public boolean canAccess(Principal principal, String trackingNo) {
        LOG.debug("Checking if owner. user={}, trackingNo={}", principal, trackingNo);

        Long customerId = jobOrderService.getCustomerId(trackingNo);
        CustomerAccount owner = customerAccountService.findByCustomerId(customerId);

        return null == owner || 
                (null != principal && owner.getAccount().getUsername().equals(principal.getName()));
    }

    public void setJobOrderService(JobOrderService jobOrderService) {
        this.jobOrderService = jobOrderService;
    }

    public void setCustomerAccountService(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }
}
