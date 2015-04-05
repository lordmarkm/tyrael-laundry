package com.tyrael.laundry.service.custom.impl;

import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
public class JobOrderServiceCustomImpl extends TyraelJpaServiceCustomImpl<JobOrder, JobOrderInfo, JobOrderService>
    implements JobOrderServiceCustom  {

    @Override
    public PageInfo<JobOrderInfo> pageInfo(String term, PageRequest pageRequest) {
        return super.pageInfo(pageRequest);
    }

    @Override
    public JobOrderInfo findByTrackinNoInfo(String trackingNo) {
        return toDto(repo.findByTrackingNo(trackingNo));
    }

}
