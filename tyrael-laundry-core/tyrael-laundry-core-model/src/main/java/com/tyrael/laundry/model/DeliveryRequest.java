package com.tyrael.laundry.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "DELIVERY_REQUEST")
public class DeliveryRequest extends TransportRequest {

    @OneToOne
    @JoinColumn(name = "JOB_ORDER_ID", nullable = false)
    private JobOrder jobOrder;

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

}
