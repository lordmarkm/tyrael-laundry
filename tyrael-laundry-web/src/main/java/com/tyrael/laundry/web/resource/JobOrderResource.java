package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baldy.commons.web.controller.GenericController;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/joborder")
public class JobOrderResource extends GenericController {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderResource.class);

    @Autowired
    private JobOrderService service;

    @RequestMapping(value = "/rql", method = GET)
    public ResponseEntity<List<JobOrderInfo>> rql(@RequestParam String term) {
        return new ResponseEntity<>(service.rqlSearch(term), OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<JobOrderInfo>> page(Principal principal,
            @RequestParam int page,
            @RequestParam int count,
            @RequestParam String term) {
        LOG.debug("JobOrder query. Principal={}, page={}, count={}, term={}", name(principal), page, count, term);
        Sort sort = new Sort(Direction.DESC, "dateReceived");
        PageRequest pageRequest = new PageRequest(page - 1, count, sort);

        return new ResponseEntity<>(service.rqlSearch(term, pageRequest), OK);
    }

    @PreAuthorize("hasRole('ROLE_POS') or hasRole('ROLE_MANAGER') or canAccess(#principal, #trackingNo)")
    @RequestMapping(method = GET, params = "trackingNo")
    public ResponseEntity<JobOrderInfo> findByTrackingNo(Principal principal, @RequestParam String trackingNo) {
        LOG.debug("Find by tracking number request. trackingNo={}", trackingNo);
        JobOrderInfo jobOrder = service.findByTrackinNoInfo(trackingNo);
        if (null == principal && null != jobOrder) {
            jobOrder.setCustomer(null);
        }
        return new ResponseEntity<>(jobOrder, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<JobOrderInfo> save(Principal principal, @RequestBody JobOrderInfo jobOrderInfo) {
        LOG.debug("Save request received. Job order form={}", jobOrderInfo);
        return new ResponseEntity<>(service.saveInfo(jobOrderInfo), OK);
    }

}
