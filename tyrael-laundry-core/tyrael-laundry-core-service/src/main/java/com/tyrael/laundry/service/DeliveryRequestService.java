package com.tyrael.laundry.service;

import java.util.List;

import com.tyrael.laundry.model.DeliveryRequest;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.reference.TransportRequestStatus;
import com.tyrael.laundry.service.custom.DeliveryRequestServiceCustom;

public interface DeliveryRequestService extends TransportRequestService<DeliveryRequest>,
    DeliveryRequestServiceCustom {

    List<DeliveryRequest> findByJobOrderAndStatus(JobOrder jobOrder, TransportRequestStatus status);

}
