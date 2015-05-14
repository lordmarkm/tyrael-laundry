package com.tyrael.laundry.service.custom;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.TransportQueue;
import com.tyrael.web.dto.TransportQueueInfo;

public interface TransportQueueServiceCustom extends TyraelJpaServiceCustom<TransportQueue, TransportQueueInfo>,
    RqlSearchingService<TransportQueue, TransportQueueInfo> {

    void addTransportRequestToQueue(String type, Long transportRequestId, Long queueId);
    TransportQueueInfo removeTransportRequestFromQueue(String type, Long transportRequestId, Long queueId);

}
