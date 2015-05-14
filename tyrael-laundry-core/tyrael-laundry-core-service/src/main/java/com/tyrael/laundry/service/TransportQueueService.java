package com.tyrael.laundry.service;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.TransportQueue;
import com.tyrael.laundry.service.custom.TransportQueueServiceCustom;

public interface TransportQueueService extends TyraelJpaService<TransportQueue>,
    TransportQueueServiceCustom {

}
