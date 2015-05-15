package com.tyrael.laundry.service.custom.impl;

import static com.tyrael.laundry.model.QDeliveryRequest.deliveryRequest;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.model.DeliveryRequest;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.model.PickupRequest;
import com.tyrael.laundry.service.DeliveryRequestService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.laundry.service.custom.DeliveryRequestServiceCustom;
import com.tyrael.web.dto.DeliveryRequestInfo;
import static com.tyrael.laundry.reference.TransportRequestStatus.*;

/**
 * 
 * @author mbmartinez
 *
 */
@Transactional
public class DeliveryRequestServiceCustomImpl
    extends AbstractTransportRequestCustomImpl<DeliveryRequest, DeliveryRequestInfo, DeliveryRequestService>
    implements DeliveryRequestServiceCustom {

    @Autowired
    private JobOrderService jobOrderService;

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

    /**
     * Need to tell the system that the job order already has an associated delivery request
     * if request status does not invalidate it. Also, set date created.
     */
    @Override
    public DeliveryRequestInfo saveInfo(DeliveryRequestInfo deliveryRequest) {
        if (null == deliveryRequest.getCreated()) {
            deliveryRequest.setCreated(DateTime.now());
        }
        DeliveryRequest entity = mapper.map(deliveryRequest, DeliveryRequest.class);
        DeliveryRequest existing = repo.findOne(deliveryRequest.getId());
        if (null != existing.getQueue()) {
            entity.setQueue(existing.getQueue());
        }

        DeliveryRequestInfo saved = toDto(repo.save(entity));

        JobOrder jobOrder = jobOrderService.findOne(saved.getJobOrder().getId());

        switch (saved.getStatus()) {
        case ADDR_INVALID:
        case ADDR_NOT_FOUND:
        case CANCELLED:
            jobOrder.setDeliveryStatus(null);
            break;
        default:
            jobOrder.setDeliveryStatus(saved.getStatus());
        }

        return saved;
    }

    @Override
    public void cancel(Long jobOrderId) {
        JobOrder jobOrder = jobOrderService.findOne(jobOrderId);
        jobOrder.setDeliveryStatus(null);

        List<DeliveryRequest> deliveryRequests = repo.findByJobOrderAndStatus(jobOrder, NEW);
        if (deliveryRequests.size() != 1) {
            throw new IllegalStateException("There should be exactly 1 delivery request with NEW status!");
        }
        deliveryRequests.get(0).setStatus(CANCELLED);
    }
}
