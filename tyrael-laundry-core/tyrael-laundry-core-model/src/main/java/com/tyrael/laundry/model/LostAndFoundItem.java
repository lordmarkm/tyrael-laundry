package com.tyrael.laundry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.laundry.reference.LostAndFoundStatus;

/**
 * @author mbmartinez
 * For lost and found items like keys and wallets.
 * Also for reporting missing articles of clothing.
 */
@Entity(name = "LOST_N_FOUND")
public class LostAndFoundItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "JOB_ORDER_ID", nullable = false)
    private JobOrder jobOrder;

    @Column(name = "LNF_DESC")
    private String description;
    
    @Column(name = "LNF_STATUS")
    @Enumerated(EnumType.STRING)
    private LostAndFoundStatus status;

    public JobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(JobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LostAndFoundStatus getStatus() {
        return status;
    }

    public void setStatus(LostAndFoundStatus status) {
        this.status = status;
    }

}
