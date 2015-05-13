package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baldy.commons.web.controller.GenericController;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.GenericHttpResponse;
import com.tyrael.laundry.service.DeliveryRequestService;
import com.tyrael.web.dto.DeliveryRequestInfo;

@RestController
@RequestMapping("/delivery")
public class DeliveryRequestResource extends GenericController {

    private static Logger LOG = LoggerFactory.getLogger(DeliveryRequestResource.class);

    @Autowired
    private DeliveryRequestService service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<DeliveryRequestInfo>> find(@RequestParam(required = false) String term, Pageable pageable) {
        LOG.debug("Finding delivery requests. term={}, page={}", term, pageable);
        return new ResponseEntity<>(service.searchRql(term, pageable), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<DeliveryRequestInfo> save(Principal principal, @RequestBody DeliveryRequestInfo deliveryRequest) {
        LOG.debug("Delivery save request. user={}, delivery req={}", name(principal), deliveryRequest);
        return new ResponseEntity<>(service.saveInfo(deliveryRequest), OK);
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<GenericHttpResponse> cancel(@RequestParam Long jobOrderId) {
        LOG.debug("Delivery cancellation requested. Job order id={}", jobOrderId);
        service.cancel(jobOrderId);
        return new ResponseEntity<>(new GenericHttpResponse("Ok", "Ok"), OK);
    }
}
