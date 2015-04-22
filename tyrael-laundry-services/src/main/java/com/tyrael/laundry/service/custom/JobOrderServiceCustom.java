package com.tyrael.laundry.service.custom;

import java.util.Map;

import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
public interface JobOrderServiceCustom extends TyraelJpaServiceCustom<JobOrder, JobOrderInfo> {

    PageInfo<JobOrderInfo> pageInfo(String term, Map<String, Object> params, String status, PageRequest pageRequest);
    JobOrderInfo findByTrackinNoInfo(String trackingNo);

}
