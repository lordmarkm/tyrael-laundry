package com.tyrael.laundry.reports.fact;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "store_dimension")
public class ModeDimension  extends BaseMeta {

    @Column(name = "store_no")
    protected int storeNumber;

    @Column(name = "store_name")
    protected String storeName;

}
