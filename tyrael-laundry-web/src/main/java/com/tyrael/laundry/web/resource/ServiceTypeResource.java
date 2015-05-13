package com.tyrael.laundry.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.commons.dto.PageInfo;
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

    @RequestMapping(value = "/all", method = GET)
    public ResponseEntity<PageInfo<ServiceTypeInfo>> findAll() {
        Pageable pageRequest = new PageRequest(0, 100, new Sort(Direction.ASC, "label"));
        return new ResponseEntity<>(service.pageInfo(pageRequest), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<List<ServiceTypeInfo>> save(@RequestBody List<ServiceTypeInfo> services) {
        return new ResponseEntity<>(service.saveInfo(services), OK);
    }

}
