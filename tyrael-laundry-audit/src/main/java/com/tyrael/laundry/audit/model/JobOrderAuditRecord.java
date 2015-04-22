package com.tyrael.laundry.audit.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.model.JobOrder;

@Entity(name = "JOB_ORDER_AUDIT_RECORD")
public class JobOrderAuditRecord extends AuditRecord {

    @ManyToOne
    @JoinColumn(name = "JOB_ORDER_ID", nullable = false)
    private JobOrder jobOrder;

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

}
