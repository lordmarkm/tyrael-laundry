package com.tyrael.laundry.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.model.JobOrder;

public interface JobOrderService extends JpaRepository<JobOrder, Long> {

    JobOrder findByTrackingNo(String trackingNo);

}
