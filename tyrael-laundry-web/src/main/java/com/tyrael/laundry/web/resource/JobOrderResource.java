package com.tyrael.laundry.web.resource;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.mysema.query.types.Order;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
@RestController
@RequestMapping("/joborder")
public class JobOrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderResource.class);

    @Autowired
    private JobOrderService service;

    @RequestMapping(method = GET)
    public ResponseEntity<PageInfo<JobOrderInfo>> page(Principal principal,
            @RequestParam int page,
            @RequestParam int count,
            @RequestParam String term,
            @RequestParam String status,
            @RequestParam(required = false) Long customer) {
        LOG.debug("JobOrder query. Principal={}, page={}, count={}, term={}, status={}", principal, page, count, term, status);
        Sort sort = new Sort(Direction.DESC, "dateReceived");
        PageRequest pageRequest = new PageRequest(page - 1, count, sort);

        Map<String, Object> params = Maps.newHashMap();
        if (null != customer) {
            params.put("customer", customer);
        }

        return new ResponseEntity<>(service.pageInfo(term, params, status, pageRequest), OK);
    }

    @RequestMapping(method = GET, params = "trackingNo")
    public ResponseEntity<JobOrderInfo> findByTrackingNo(Principal principal, @RequestParam String trackingNo) {
        LOG.debug("Find by tracking number request. trackingNo={}", trackingNo);
        return new ResponseEntity<>(service.findByTrackinNoInfo(trackingNo), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<JobOrderInfo> save(Principal principal, @RequestBody JobOrderInfo jobOrderInfo) {
        LOG.debug("Save request received. Job order form={}", jobOrderInfo);
        return new ResponseEntity<>(service.saveInfo(jobOrderInfo), OK);
    }

}
