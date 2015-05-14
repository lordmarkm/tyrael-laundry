package com.tyrael.laundry.service.custom.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.laundry.model.DeliveryRequest;
import com.tyrael.laundry.model.PickupRequest;
import com.tyrael.laundry.model.TransportQueue;
import com.tyrael.laundry.reference.TransportRequestStatus;
import com.tyrael.laundry.service.DeliveryRequestService;
import com.tyrael.laundry.service.PickupRequestService;
import com.tyrael.laundry.service.TransportQueueService;
import com.tyrael.laundry.service.custom.TransportQueueServiceCustom;
import com.tyrael.web.dto.TransportQueueInfo;
import static com.tyrael.laundry.model.QTransportRequest.transportRequest;

@Transactional
public class TransportQueueServiceCustomImpl 
    extends RqlSearchingServiceImpl<TransportQueue, TransportQueueInfo, TransportQueueService>
    implements TransportQueueServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(TransportQueueServiceCustomImpl.class);
    private static final String TYPE_PICKUP = "PICKUP";
    private static final String TYPE_DELIVERY = "DELIVERY";

    @Autowired
    private PickupRequestService pickupService;

    @Autowired
    private DeliveryRequestService deliveryService;

    @Autowired
    private TransportQueueService service;

    @Override
    protected ImmutableMap<String, Path<?>> fieldMapping() {
        return ImmutableMap.<String, Path<?>>builder()
                .put("id", transportRequest.id)
                .build();
    }

    @Override
    public void addTransportRequestToQueue(String type, Long transportRequestId, Long queueId) {
        TransportQueue q = service.findOne(queueId);
        if (TYPE_PICKUP.equals(type)) {
            LOG.debug("Adding pickup request to queue. req id={}, q id={}", transportRequestId, queueId);
            PickupRequest pickupRequest = pickupService.findOne(transportRequestId);
            pickupRequest.setQueue(q);
            pickupRequest.setStatus(TransportRequestStatus.QUEUED);
        } else if (TYPE_DELIVERY.equals(type)) {
            LOG.debug("Adding delivery request to queue. req id={}, q id={}", transportRequestId, queueId);
            DeliveryRequest deliveryRequest = deliveryService.findOne(transportRequestId);
            deliveryRequest.setQueue(q);
            deliveryRequest.setStatus(TransportRequestStatus.QUEUED);
        } else {
            throw new IllegalArgumentException("Unknown transport type: " + type);
        }
    }

    @Override
    public TransportQueueInfo removeTransportRequestFromQueue(String type, Long transportRequestId, Long queueId) {

        if (TYPE_PICKUP.equals(type)) {
            LOG.debug("Removing pickup request from its current queue. req id={}", transportRequestId);
            PickupRequest pickupRequest = pickupService.findOne(transportRequestId);
            pickupRequest.setQueue(null);
            pickupRequest.setStatus(TransportRequestStatus.NEW);
            pickupService.saveAndFlush(pickupRequest);
        } else if (TYPE_DELIVERY.equals(type)) {
            LOG.debug("Removing delivery request from its current queue. req id={}", transportRequestId);
            DeliveryRequest deliveryRequest = deliveryService.findOne(transportRequestId);
            deliveryRequest.setQueue(null);
            deliveryRequest.setStatus(TransportRequestStatus.NEW);
            deliveryService.saveAndFlush(deliveryRequest);
        } else {
            throw new IllegalArgumentException("Unknown transport type: " + type);
        }

        return findOneInfo(queueId);
    }

}
