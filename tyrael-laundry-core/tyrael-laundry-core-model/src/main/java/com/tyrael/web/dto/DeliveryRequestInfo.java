package com.tyrael.web.dto;

import org.springframework.core.style.ToStringCreator;

/**
 * @author mbmartinez
 */
public class DeliveryRequestInfo extends TransportRequestInfo {

    private JobOrderInfo jobOrder;

    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("jobOrder", jobOrder);
    }

    public JobOrderInfo getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrderInfo jobOrder) {
        this.jobOrder = jobOrder;
    }

}
