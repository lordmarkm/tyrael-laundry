package com.tyrael.laundry.service.custom.impl;

import static com.tyrael.laundry.model.QDeliveryRequest.deliveryRequest;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.model.DeliveryRequest;
import com.tyrael.laundry.service.DeliveryRequestService;
import com.tyrael.laundry.service.custom.DeliveryRequestServiceCustom;
import com.tyrael.web.dto.DeliveryRequestInfo;

public class DeliveryRequestServiceCustomImpl
    extends AbstractTransportRequestCustomImpl<DeliveryRequest, DeliveryRequestInfo, DeliveryRequestService>
    implements DeliveryRequestServiceCustom {

    @Override
    protected ImmutableMap<String, Path<?>> fieldMapping() {
        return ImmutableMap.<String, Path<?>>builder()
            .put("id", deliveryRequest.id)
            .put("jobOrderTrackingNo", deliveryRequest.jobOrder.trackingNo)
            .put("address", deliveryRequest.address)
            .put("customerId", deliveryRequest.customer.id)
            .put("customerSurname", deliveryRequest.customer.name.surname)
            .put("customerGivenName", deliveryRequest.customer.name.givenName)
            .put("created", deliveryRequest.created)
            .put("completed", deliveryRequest.completed)
            .put("status", deliveryRequest.status)
            .build();
    }

}
