package com.tyrael.laundry.reports.fact;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "item_dimension")
public class ItemDimension extends BaseMeta {

    @Column(name = "item_id")
    protected Long itemId;

    @Column(name = "item_descr")
    protected String itemDescription;

}
