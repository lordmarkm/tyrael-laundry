package com.tyrael.laundry.reports.fact;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.tyrael.laundry.reports.base.BaseMeta;

@Entity(name = "mode_dimension")
public class StoreDimension  extends BaseMeta {

    @Column(name = "mode_id")
    protected int modeId;

    @Column(name = "mode_descr")
    protected String modeDescription;

}
