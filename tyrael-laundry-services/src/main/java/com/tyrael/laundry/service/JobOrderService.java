package com.tyrael.laundry.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;

/**
 * @author mbmartinez
 */
public interface JobOrderService extends JobOrderServiceCustom, JpaRepository<JobOrder, Long> {

    JobOrder findByTrackingNo(String trackingNo);

}
