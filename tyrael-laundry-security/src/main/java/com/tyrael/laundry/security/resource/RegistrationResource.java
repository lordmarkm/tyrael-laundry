package com.tyrael.laundry.security.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.GenericHttpResponse;
import com.tyrael.laundry.security.dto.RegistrationForm;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/register")
public class RegistrationResource {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationResource.class);

    @RequestMapping(method = POST)
    public ResponseEntity<GenericHttpResponse> register(@RequestBody @Valid RegistrationForm regForm,
            BindingResult bindingResult) {
        LOG.debug("Registration request. form={}", regForm);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new GenericHttpResponse(null, bindingResult.getAllErrors().get(0).getDefaultMessage()), NOT_ACCEPTABLE);
        } else if (!regForm.getPassword().equals(regForm.getConfirmPassword())) {
            return new ResponseEntity<>(new GenericHttpResponse(null,"Passwords don't match"), NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(new GenericHttpResponse(null, "OK"), ACCEPTED);
        }
    }

}
