package com.tyrael.laundry.billing.resource;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.billing.dto.CustomerBillingInfo;
import com.tyrael.laundry.billing.service.CustomerBillingService;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/billing/customer")
public class CustomerBillingResource {

    private static Logger LOG = LoggerFactory.getLogger(CustomerBillingResource.class);

    @Autowired
    private CustomerBillingService service;

    @RequestMapping(value = "/{customerId}", method = GET)
    public ResponseEntity<CustomerBillingInfo> getCustomerBillingInfo(@PathVariable Long customerId) {
        LOG.debug("Preparing customer billing info. customerId={}", customerId);
        return new ResponseEntity<>(service.prepareBillingInfo(customerId), OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<CustomerBillingInfo> getCustomerBillingInfo(Principal principal) {
        LOG.debug("Preparing customer billing info for current user. username={}", principal.getName());
        return new ResponseEntity<>(service.prepareBillingInfo(principal.getName()), OK);
    }

}
