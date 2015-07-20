package com.tyrael.laundry.reports.fact;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "check_items_fact")
public class CheckItemsFact extends BaseMeta {

    @JoinColumn(name = "mode_dim_id")
    @ManyToOne(optional = false)
    protected ModeDimension mode;

    @JoinColumn(name = "item_dim_id")
    @ManyToOne(optional = false)
    protected ItemDimension item;

    @Column(name = "check_id", nullable = false)
    protected Long checkId;

    @Column(name = "item_cnt")
    protected int itemCount;

    @Column(name = "item_amt")
    protected BigDecimal itemAmount;

}
