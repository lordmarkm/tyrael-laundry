package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.commons.dto.GenericHttpResponse;
import com.tyrael.laundry.service.TransportQueueService;
import com.tyrael.web.dto.TransportQueueInfo;

@RestController
@RequestMapping("/transportq")
public class TransportQueueResource {

    @Autowired
    private TransportQueueService service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<TransportQueueInfo>> query(Pageable pageRequest, @RequestParam String term) {
        return new ResponseEntity<>(service.searchRql(term, pageRequest), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<TransportQueueInfo> createNew(@RequestBody TransportQueueInfo transportQueueInfo) {
        return new ResponseEntity<>(service.saveInfo(transportQueueInfo), OK);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<GenericHttpResponse> addTransportRequestToQueue(
            @RequestParam String type, @RequestParam Long transportRequestId, @RequestParam Long queueId) {
        service.addTransportRequestToQueue(type, transportRequestId, queueId);
        return new ResponseEntity<>(new GenericHttpResponse(), OK);
    }

    @RequestMapping(method = DELETE)
    public ResponseEntity<TransportQueueInfo> removeFromQueue(
            @RequestParam String type, @RequestParam Long transportRequestId, @RequestParam Long queueId) {
        return new ResponseEntity<>(service.removeTransportRequestFromQueue(type, transportRequestId, queueId), OK);
    }

}
