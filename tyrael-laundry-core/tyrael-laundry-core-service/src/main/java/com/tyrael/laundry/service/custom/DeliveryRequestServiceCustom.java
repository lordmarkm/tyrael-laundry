package com.tyrael.laundry.service.custom;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.DeliveryRequest;
import com.tyrael.web.dto.DeliveryRequestInfo;

/**
 * @author mbmartinez
 */
public interface DeliveryRequestServiceCustom  extends TransportRequestCustom<DeliveryRequest, DeliveryRequestInfo>,
    TyraelJpaServiceCustom<DeliveryRequest, DeliveryRequestInfo> {

    public void cancel(Long jobOrderId);

}
