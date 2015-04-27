package com.tyrael.laundry.sequence;

import javax.persistence.Entity;

import com.tyrael.commons.models.PersistentSequence;

/**
 * @author mbmartinez
 */
@Entity(name = "JOB_ORDER_TRACKING_NO_SEQUENCE")
public class TyraelLaundryJobOrderSequence extends PersistentSequence {

    @Override
    protected String getDelimiter() {
        return "";
    }

}
