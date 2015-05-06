package com.tyrael.laundry.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.baldy.commons.models.BaseEntity;
import com.baldy.commons.models.proper.Address;
import com.baldy.commons.models.proper.ContactDetails;
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

    @Embedded
    private ContactDetails contactDetails;

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

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

}
