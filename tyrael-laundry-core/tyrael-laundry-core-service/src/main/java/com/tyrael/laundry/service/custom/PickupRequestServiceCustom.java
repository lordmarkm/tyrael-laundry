package com.tyrael.laundry.service.custom;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.PickupRequest;
import com.tyrael.web.dto.TransportRequestInfo;

public interface PickupRequestServiceCustom extends TransportRequestCustom<PickupRequest, TransportRequestInfo>,
    TyraelJpaServiceCustom<PickupRequest, TransportRequestInfo> {

}
