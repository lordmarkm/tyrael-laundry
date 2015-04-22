package com.tyrael.web.dto;

import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.laundry.reference.LostAndFoundStatus;

/**
 * @author mbmartinez
 */
public class LostAndFoundItemInfo extends BaseTyraelDto {

    private String description;
    private LostAndFoundStatus status;

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
