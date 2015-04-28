package com.tyrael.laundry.security.resource;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.commons.dto.GenericHttpResponse;
import com.tyrael.laundry.security.dto.RegistrationForm;
import com.tyrael.laundry.security.service.RegistrationService;

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
            try {
                registrationService.register(regForm);
            } catch (Exception e) {
                return new ResponseEntity<>(new GenericHttpResponse(null, e.getMessage()), BAD_REQUEST);
            }
            return new ResponseEntity<>(new GenericHttpResponse(null, "OK"), ACCEPTED);
        }
    }

}
