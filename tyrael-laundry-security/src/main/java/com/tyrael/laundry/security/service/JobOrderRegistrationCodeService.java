package com.tyrael.laundry.security.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.security.model.JobOrderRegistrationCode;

/**
 * @author mbmartinez
 */
public interface JobOrderRegistrationCodeService extends JpaRepository<JobOrderRegistrationCode, Long> {

    JobOrderRegistrationCode findByJobOrder_TrackingNo(String jobOrderTrackingNo);
    List<JobOrderRegistrationCode> findByJobOrder_Customer(Customer customer);

}
