package com.tyrael.laundry.service;

import com.tyrael.laundry.model.DeliveryRequest;
import com.tyrael.laundry.model.PickupRequest;
import com.tyrael.laundry.service.custom.DeliveryRequestServiceCustom;
import com.tyrael.laundry.service.custom.PickupRequestServiceCustom;

public interface DeliveryRequestService extends TransportRequestService<DeliveryRequest>,
    DeliveryRequestServiceCustom {

}
