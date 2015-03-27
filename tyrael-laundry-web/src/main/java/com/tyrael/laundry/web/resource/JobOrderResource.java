package com.tyrael.laundry.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.JobOrderService;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author mbmartinez
 */
@Controller
@RequestMapping("/joborder")
public class JobOrderResource {

    @Autowired
    private JobOrderService service;

    @RequestMapping(method = GET)
    public ResponseEntity<List<JobOrder>> findAll() {
        List<JobOrder> jobOrders = service.findAll();
        return new ResponseEntity<>(jobOrders, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<JobOrder> save(JobOrder jobOrder) {
        JobOrder saved = service.save(jobOrder);
        return new ResponseEntity<>(saved, OK);
    }

}
