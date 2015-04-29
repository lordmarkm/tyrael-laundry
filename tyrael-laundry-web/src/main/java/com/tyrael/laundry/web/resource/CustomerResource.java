package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baldy.commons.web.controller.GenericController;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.CustomerService;
import com.tyrael.web.dto.CustomerInfo;

@RestController
@RequestMapping("/customer")
public class CustomerResource extends GenericController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

    @Autowired
    private CustomerService service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<CustomerInfo>> page(Principal principal,
            @RequestParam int page,
            @RequestParam int count,
            @RequestParam String term) {
        LOG.debug("Customer query. Principal={}, page={}, count={}, term={}", principal, page, count, term);
        PageRequest pageRequest = new PageRequest(page - 1, count);
        return new ResponseEntity<>(service.pageInfo(term, pageRequest), OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<CustomerInfo> findOne(Principal principal, @PathVariable Long id) {
        return new ResponseEntity<>(service.findOneInfo(id), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<CustomerInfo> save(Principal principal, @RequestBody CustomerInfo customer) {
        LOG.debug("Customer save request. user={}, customer={}", name(principal), customer);
        return new ResponseEntity<>(service.saveInfo(customer), OK);
    }
}
