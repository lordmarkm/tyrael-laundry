package com.tyrael.laundry.security.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baldy.commons.security.services.AccountService;
import com.tyrael.laundry.commons.dto.GenericHttpResponse;
import com.tyrael.laundry.security.dto.RegistrationForm;
import com.tyrael.laundry.security.service.RegistrationService;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/register")
public class RegistrationResource {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationResource.class);

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = POST)
    public ResponseEntity<GenericHttpResponse> register(@RequestBody @Valid RegistrationForm regForm,
            BindingResult bindingResult) {
        LOG.debug("Registration request. form={}", regForm);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new GenericHttpResponse(null, bindingResult.getAllErrors().get(0).getDefaultMessage()), BAD_REQUEST);
        } else if (!regForm.getPassword().equals(regForm.getConfirmPassword())) {
            return new ResponseEntity<>(new GenericHttpResponse(null,"Passwords don't match"), BAD_REQUEST);
        } else {
            registrationService.register(regForm.getJobOrderTrackingNo(), regForm.getUsername(), regForm.getPassword());
            return new ResponseEntity<>(new GenericHttpResponse(null, "OK"), ACCEPTED);
        }
    }

}
