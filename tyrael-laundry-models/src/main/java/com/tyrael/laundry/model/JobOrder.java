package com.tyrael.laundry.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.laundry.reference.JobOrderStatus;

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

    @Column(name = "DATE_COMPLETED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateCompleted;

    @Column(name = "DATE_CLAIMED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateClaimed;

    @Column(name = "TRACKING_NO", nullable = false, unique = true)
    private String trackingNo;

    @OneToMany(mappedBy = "jobOrder", cascade = CascadeType.ALL)
    private List<JobService> jobServices;

    @OneToMany(mappedBy = "jobOrder", cascade = CascadeType.ALL)
    private List<JobItem> jobItems;

    @Column(name = "TOTAL_AMT", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "TOTAL_AMT_PAID", nullable = false)
    private BigDecimal totalAmountPaid;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobOrderStatus status;

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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DateTime getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(DateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public DateTime getDateClaimed() {
        return dateClaimed;
    }

    public void setDateClaimed(DateTime dateClaimed) {
        this.dateClaimed = dateClaimed;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public JobOrderStatus getStatus() {
        return status;
    }

    public void setStatus(JobOrderStatus status) {
        this.status = status;
    }

}
