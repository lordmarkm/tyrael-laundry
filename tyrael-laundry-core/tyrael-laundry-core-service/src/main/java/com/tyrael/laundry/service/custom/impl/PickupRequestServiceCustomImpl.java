package com.tyrael.laundry.service.custom.impl;

import static com.tyrael.laundry.model.QPickupRequest.pickupRequest;

import org.joda.time.DateTime;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.model.PickupRequest;
import com.tyrael.laundry.service.PickupRequestService;
import com.tyrael.laundry.service.custom.PickupRequestServiceCustom;
import com.tyrael.web.dto.TransportRequestInfo;

public class PickupRequestServiceCustomImpl
    extends AbstractTransportRequestCustomImpl<PickupRequest, TransportRequestInfo, PickupRequestService>
    implements PickupRequestServiceCustom {

    @Override
    protected ImmutableMap<String, Path<?>> fieldMapping() {
        return ImmutableMap.<String, Path<?>>builder()
            .put("id", pickupRequest.id)
            .put("deleted", pickupRequest.deleted)
            .put("address", pickupRequest.address)
            .put("customerId", pickupRequest.customer.id)
            .put("customerSurname", pickupRequest.customer.name.surname)
            .put("customerGivenName", pickupRequest.customer.name.givenName)
            .put("created", pickupRequest.created)
            .put("completed", pickupRequest.completed)
            .put("status", pickupRequest.status)
            .build();
    }

    @Override
    public TransportRequestInfo saveInfo(TransportRequestInfo pickupRequest) {
        if (null == pickupRequest.getCreated()) {
            pickupRequest.setCreated(DateTime.now());
        }
        return super.saveInfo(pickupRequest);
    }
}
