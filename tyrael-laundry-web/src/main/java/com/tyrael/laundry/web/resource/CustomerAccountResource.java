package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.security.dto.CustomerAccountInfo;
import com.tyrael.laundry.security.service.CustomerAccountService;

@RestController
@RequestMapping("/customeraccount")
public class CustomerAccountResource {

    @Autowired
    private CustomerAccountService customerAccountService;

    @RequestMapping(value = "/current")
    public ResponseEntity<CustomerAccountInfo> getCurrentlyLoggedIn(Principal principal) {
        return new ResponseEntity<>(customerAccountService.findByAccountUsernameInfo(principal.getName()), OK);
    }

}
