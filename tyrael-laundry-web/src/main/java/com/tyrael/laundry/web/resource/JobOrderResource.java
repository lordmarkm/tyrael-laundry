package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
@Controller
@RequestMapping("/joborder")
public class JobOrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderResource.class);

    @Autowired
    private JobOrderService service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<JobOrderInfo>> page(Principal principal,
            @RequestParam int page,
            @RequestParam int count,
            @RequestParam String term) {
        LOG.debug("JobOrder query. Principal={}, page={}, count={}, term={}", principal, page, count, term);
        PageRequest pageRequest = new PageRequest(page - 1, count);
        return new ResponseEntity<>(service.pageInfo(term, pageRequest), OK);
    }

    @RequestMapping(method = GET, params = "trackingNo")
    public ResponseEntity<JobOrderInfo> findByTrackingNo(Principal principal, @RequestParam String trackingNo) {
        LOG.debug("Find by tracking number request. trackingNo={}", trackingNo);
        return new ResponseEntity<>(service.findByTrackinNoInfo(trackingNo), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<JobOrderInfo> save(Principal principal, JobOrderInfo jobOrderInfo) {
        LOG.debug("Save request received. Job order form={}", jobOrderInfo);
        return new ResponseEntity<>(service.saveInfo(jobOrderInfo), OK);
    }

}
