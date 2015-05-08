package com.tyrael.laundry.service;

import com.tyrael.laundry.model.PickupRequest;
import com.tyrael.laundry.service.custom.PickupRequestServiceCustom;

public interface PickupRequestService extends TransportRequestService<PickupRequest>,
    PickupRequestServiceCustom {

}
