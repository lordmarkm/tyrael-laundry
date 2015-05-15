package com.tyrael.laundry.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseEntity;

@Entity(name = "TRANSPORT_QUEUE")
public class TransportQueue extends BaseEntity {

    @Column(name = "DATE_CREATED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @OneToMany(mappedBy = "queue")
    private Set<PickupRequest> pickupRequests;

    @OneToMany(mappedBy = "queue")
    private Set<DeliveryRequest> deliveryRequests;

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public Set<PickupRequest> getPickupRequests() {
        return pickupRequests;
    }

    public void setPickupRequests(Set<PickupRequest> pickupRequests) {
        this.pickupRequests = pickupRequests;
    }

    public Set<DeliveryRequest> getDeliveryRequests() {
        return deliveryRequests;
    }

    public void setDeliveryRequests(Set<DeliveryRequest> deliveryRequests) {
        this.deliveryRequests = deliveryRequests;
    }

}
