package com.tyrael.laundry.reports.fact;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "JOB_ORDER_FACT")
public class JobOrderFact extends BaseMeta {

    @Column(name = "CUSTOMER_NAME")
    protected String customerName;

    @Column(name = "DATE_RECEIVED_DIM_ID")
    protected Long dateReceivedDimId;

    @Column(name = "DATE_DUE_DIM_ID")
    protected Long dateDueDimId;

    @Column(name = "DATE_COMPLETED_DIM_ID")
    protected Long dateCompletedDimId;

    @Column(name = "DATE_CLAIMED_DIM_ID")
    protected Long dateClaimedDimId;

    @Column(name = "TRACKING_NO")
    protected String trackingNo;

    @Column(name = "TOTAL_AMT")
    protected BigDecimal totalAmount;

    @Column(name = "TOTAL_AMT_PAID")
    protected BigDecimal totalAmountPaid;

    @Column(name = "STATUS")
    protected String status;

    @Column(name = "DELIVERY_STATUS")
    protected String deliveryStatus;

    @Column(name = "WT_KG")
    protected BigDecimal weightInKilos;

}
