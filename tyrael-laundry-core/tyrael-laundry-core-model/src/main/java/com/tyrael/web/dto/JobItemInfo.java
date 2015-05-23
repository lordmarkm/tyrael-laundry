package com.tyrael.web.dto;

import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.laundry.reference.JobItemType;

/**
 * @author markm
 */
public class JobItemInfo extends BaseTyraelDto {

    private JobItemType jobItemType;
    private int quantity;
    public JobItemType getJobItemType() {
        return jobItemType;
    }
    public void setJobItemType(JobItemType jobItemType) {
        this.jobItemType = jobItemType;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

