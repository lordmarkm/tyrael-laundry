package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.PickupRequestService;
import com.tyrael.web.dto.TransportRequestInfo;

@RestController
@RequestMapping("/pickup")
public class PickupRequestResource {

    private static Logger LOG = LoggerFactory.getLogger(PickupRequestResource.class);

    @Autowired
    private PickupRequestService service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<TransportRequestInfo>> find(@RequestParam(required = false) String term, Pageable pageable) {
        LOG.debug("Finding pickup requests. term={}, page={}", term, pageable);
        return new ResponseEntity<>(service.searchRql(term, pageable), OK);
    }
}
