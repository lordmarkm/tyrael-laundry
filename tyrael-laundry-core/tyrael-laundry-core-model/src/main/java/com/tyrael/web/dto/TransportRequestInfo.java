package com.tyrael.web.dto;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.AddressInfo;
import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.laundry.reference.TransportRequestStatus;

/**
 * @author mbmartinez
 */
public class TransportRequestInfo extends BaseTyraelDto {

    private CustomerInfo customer;
    private AddressInfo address;
    private DateTime created;
    private DateTime completed;
    private TransportRequestStatus status;

    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("customer", customer)
            .append("addr", address)
            .append("created", created)
            .append("completed", completed)
            .append("status", status);
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getCompleted() {
        return completed;
    }

    public void setCompleted(DateTime completed) {
        this.completed = completed;
    }

    public TransportRequestStatus getStatus() {
        return status;
    }

    public void setStatus(TransportRequestStatus status) {
        this.status = status;
    }

}
