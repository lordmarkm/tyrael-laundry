package com.tyrael.laundry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.laundry.reference.JobItemType;

/**
 * @author mbmartinez
 */
@Entity(name = "JOB_ITEM")
public class JobItem extends BaseEntity {

    @Column(name = "ITEM_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private JobItemType jobItemType;

    @Column(name = "QTY", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "JOB_ID", nullable = false)
    private JobOrder jobOrder;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

    public JobItemType getJobItemType() {
        return jobItemType;
    }

    public void setJobItemType(JobItemType jobItemType) {
        this.jobItemType = jobItemType;
    }

}
