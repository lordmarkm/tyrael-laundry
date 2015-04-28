package com.tyrael.laundry.service.custom;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.model.QJobOrder;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
@Transactional
public interface JobOrderServiceCustom extends TyraelJpaServiceCustom<JobOrder, JobOrderInfo> {

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("trackingNo", QJobOrder.jobOrder.trackingNo)
            .put("id", QJobOrder.jobOrder.id)
            .build();

    PageInfo<JobOrderInfo> pageInfo(String term, Map<String, Object> params, String status, PageRequest pageRequest);
    JobOrderInfo findByTrackinNoInfo(String trackingNo);
    List<JobOrderInfo> rqlSearch(String term);

}
