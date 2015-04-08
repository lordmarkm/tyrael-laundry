package com.tyrael.web.dto;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.BaseTyraelDto;

/**
 * @author mbmartinez
 */
public class JobOrderInfo extends BaseTyraelDto {

    private CustomerInfo customer;
    private DateTime dateReceived;
    private DateTime dateDue;
    private String trackingNo;
    private List<JobServiceInfo> jobServices;
    private List<JobItemInfo> jobItems;
    private BigDecimal totalAmount;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("tracking no.", trackingNo)
            .append("customer", customer)
            .append("date rcvd", dateReceived)
            .append("date due", dateDue)
            .append("job service", jobServices)
            .append("job items", jobItems)
            .append("total amt", totalAmount);
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public DateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(DateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public DateTime getDateDue() {
        return dateDue;
    }

    public void setDateDue(DateTime dateDue) {
        this.dateDue = dateDue;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public List<JobServiceInfo> getJobServices() {
        return jobServices;
    }

    public void setJobServices(List<JobServiceInfo> jobServices) {
        this.jobServices = jobServices;
    }

    public List<JobItemInfo> getJobItems() {
        return jobItems;
    }

    public void setJobItems(List<JobItemInfo> jobItems) {
        this.jobItems = jobItems;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
