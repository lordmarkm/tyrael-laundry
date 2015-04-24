package com.tyrael.laundry.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.laundry.model.JobOrder;

@Entity(name = "JOB_ORDER_REG_CODE")
public class JobOrderRegistrationCode extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "JOB_ORDER_ID", nullable = false)
    private JobOrder jobOrder;

    @Column(name = "REG_CODE", nullable = false)
    private String registrationCode;

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

}
