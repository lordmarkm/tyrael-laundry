package com.tyrael.laundry.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseEntity;
import com.baldy.commons.models.proper.Address;
import com.tyrael.laundry.reference.TransportRequestStatus;

@MappedSuperclass
public class TransportRequest extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    /**
     * Separate from customer.address as that field may change but this should not.
     * Also we might allow customers to request pickup/delivery to addresses different from
     * customer.address
     *
     * Also, override line 1 as required otherwise we wouldn't know where to deliver to.
     */
    @Embedded
    @AttributeOverride(name = "addressLine1", column = @Column(name = "ADDR_LINE1", nullable = false))
    private Address address;

    @Column(name = "DATE_CREATED", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Column(name = "DATE_COMPLETED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime completed;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportRequestStatus status;

    @ManyToOne
    @JoinColumn(name = "QUEUE")
    private TransportQueue queue;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    public TransportQueue getQueue() {
        return queue;
    }

    public void setQueue(TransportQueue queue) {
        this.queue = queue;
    }

}
