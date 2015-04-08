package com.tyrael.laundry.service;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;

/**
 * @author mbmartinez
 */
public interface JobOrderService extends JobOrderServiceCustom, TyraelJpaService<JobOrder> {

    JobOrder findByTrackingNo(String trackingNo);

}
