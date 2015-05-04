package com.tyrael.laundry.service.custom;

import static com.tyrael.laundry.model.QJobOrder.jobOrder;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
@Transactional
public interface JobOrderServiceCustom extends TyraelJpaServiceCustom<JobOrder, JobOrderInfo> {

    ImmutableMap<String, Path<?>> FIELD_MAPPING = ImmutableMap.<String, Path<?>>builder()
            .put("trackingNo", jobOrder.trackingNo)
            .put("id", jobOrder.id)
            .put("deleted", jobOrder.deleted)
            .put("dateReceived", jobOrder.dateReceived)
            .put("customerId", jobOrder.customer.id)
            .put("customerSurname", jobOrder.customer.name.surname)
            .put("customerGivenName", jobOrder.customer.name.givenName)
            .put("status", jobOrder.status)
            .build();

    JobOrderInfo findByTrackinNoInfo(String trackingNo);
    List<JobOrderInfo> rqlSearch(String term);
    PageInfo<JobOrderInfo> rqlSearch(String term, Pageable pageRequest);

}
