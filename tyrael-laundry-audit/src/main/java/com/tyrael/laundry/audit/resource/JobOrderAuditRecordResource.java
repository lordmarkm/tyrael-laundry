package com.tyrael.laundry.audit.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.audit.dto.AuditRecordInfo;
import com.tyrael.laundry.audit.service.JobOrderAuditRecordService;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/audit/joborder")
public class JobOrderAuditRecordResource {

    @Autowired
    private JobOrderAuditRecordService service;

    @RequestMapping(value = "/{trackingNo}", method = GET)
    public ResponseEntity<List<AuditRecordInfo>> findByTrackingNo(@PathVariable String trackingNo) {
        return new ResponseEntity<>(service.findByJobOrderTrackingNoInfo(trackingNo), OK);
    }

}
