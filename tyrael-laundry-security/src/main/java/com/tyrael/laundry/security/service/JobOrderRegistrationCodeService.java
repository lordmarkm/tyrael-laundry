package com.tyrael.laundry.security.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.security.model.JobOrderRegistrationCode;

/**
 * @author mbmartinez
 */
public interface JobOrderRegistrationCodeService extends JpaRepository<JobOrderRegistrationCode, Long> {

}
