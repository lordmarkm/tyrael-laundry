package com.tyrael.laundry.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseEntity;

/**
 * @author mbmartinez
 */
@Entity(name = "JOB_ORDER")
public class JobOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "DATE_RECEIVED", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateReceived;

    @Column(name = "DATE_DUE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateDue;

    @Column(name = "TRACKING_NO", nullable = false)
    private String trackingNo;

    @OneToMany(mappedBy = "jobOrder", cascade = CascadeType.ALL)
    private List<JobService> jobServices;

    @OneToMany(mappedBy = "jobOrder", cascade = CascadeType.ALL)
    private List<JobItem> jobItems;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

    public List<JobService> getJobServices() {
        return jobServices;
    }

    public void setJobServices(List<JobService> jobServices) {
        this.jobServices = jobServices;
    }

    public List<JobItem> getJobItems() {
        return jobItems;
    }

    public void setJobItems(List<JobItem> jobItems) {
        this.jobItems = jobItems;
    }

}
