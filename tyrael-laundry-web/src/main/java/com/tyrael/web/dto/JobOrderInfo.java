package com.tyrael.web.dto;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.reference.ServiceType;

public class JobOrderInfo {

    private Long id;
    private String lastName;
    private String trackingNo;
    private ServiceType serviceType;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("cust last name", lastName)
            .append("tracking no", trackingNo)
            .append("service type", serviceType)
            .toString();
    }

    public JobOrder toJobOrder() {
        JobOrder jo = new JobOrder();
        jo.setTrackingNo(trackingNo);
        return jo;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getTrackingNo() {
        return trackingNo;
    }
    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }
    public ServiceType getServiceType() {
        return serviceType;
    }
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
