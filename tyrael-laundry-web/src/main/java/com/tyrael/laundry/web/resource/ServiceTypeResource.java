package com.tyrael.laundry.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.service.ServiceTypeService;
import com.tyrael.web.dto.ServiceTypeInfo;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/servicetype")
public class ServiceTypeResource {

    @Autowired
    private ServiceTypeService service;

    @RequestMapping(method = GET)
    public ResponseEntity<List<ServiceTypeInfo>> findEnabled() {
        return new ResponseEntity<>(service.findByEnabledInfo(), OK);
    }
}
