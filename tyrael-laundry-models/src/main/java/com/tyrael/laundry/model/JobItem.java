package com.tyrael.laundry.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.baldy.commons.models.BaseEntity;

/**
 * @author mbmartinez
 */
@Entity(name = "JOB_ITEM")
public class JobItem extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
