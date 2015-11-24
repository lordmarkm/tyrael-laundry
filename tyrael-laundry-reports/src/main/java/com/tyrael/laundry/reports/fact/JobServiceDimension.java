package com.tyrael.laundry.reports.fact;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Mark Martinez, create Nov 24, 2015
 *
 */
public class JobServiceDimension {

    @Column(name = "SERVICE_TYPE_CODE", nullable = false)
    protected String serviceTypeCode;

    @Column(name = "WT_KG", nullable = false)
    protected BigDecimal weightInKilos;

    @Column(name = "PRICE_KG", nullable = false)
    protected BigDecimal pricePerKilo;

    @Column(name = "AMOUNT", nullable = false)
    protected BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "JOB_ORDER_FACT_ID", nullable = false)
    protected JobOrderFact jobOrder;

}
