package com.tyrael.laundry.reports.fact;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "check_fact")
public class CheckFact extends BaseMeta {

    @ManyToOne(optional = false)
    @JoinColumn(name = "trans_dt_dim_id")
    protected DateDimension transactionDateDimId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_dim_id")
    protected StoreDimension storeDimensionId;

    @Column(name = "check_id")
    protected Long checkId;

    @Column(name = "check_amt")
    protected BigDecimal checkAmount;

    @Column(name = "taxes_amt")
    protected BigDecimal taxesAmount;

    @Column(name = "comps_amt")
    protected BigDecimal comps_amt;

    @Column(name = "promos_amt")
    protected BigDecimal promos_amt;

    @Column(name = "sales_amt")
    protected BigDecimal sales_amt;

}
