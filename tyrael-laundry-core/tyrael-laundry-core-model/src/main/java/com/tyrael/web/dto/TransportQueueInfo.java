package com.tyrael.web.dto;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.BaseTyraelDto;

/**
 * 
 * @author mbmartinez
 *
 */
public class TransportQueueInfo extends BaseTyraelDto {

    private DateTime created;
    private List<DeliveryRequestInfo> deliveryRequests;
    private List<TransportRequestInfo> pickupRequests;
    
    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
                .append("created", created)
                .append("deliveries", deliveryRequests)
                .append("pickups", pickupRequests);
    }
    
    public DateTime getCreated() {
        return created;
    }
    public void setCreated(DateTime created) {
        this.created = created;
    }
    public List<DeliveryRequestInfo> getDeliveryRequests() {
        return deliveryRequests;
    }
    public void setDeliveryRequests(List<DeliveryRequestInfo> deliveryRequests) {
        this.deliveryRequests = deliveryRequests;
    }
    public List<TransportRequestInfo> getPickupRequests() {
        return pickupRequests;
    }
    public void setPickupRequests(List<TransportRequestInfo> pickupRequests) {
        this.pickupRequests = pickupRequests;
    }

}
