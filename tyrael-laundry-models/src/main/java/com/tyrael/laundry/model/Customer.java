package com.tyrael.laundry.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.baldy.commons.models.BaseEntity;
import com.baldy.commons.models.proper.Address;
import com.baldy.commons.models.proper.Name;

/**
 * @author mbmartinez
 */
@Entity(name = "CUSTOMER")
public class Customer extends BaseEntity {

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Column(name = "CONTACT_NO")
    private String contactNumber;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
