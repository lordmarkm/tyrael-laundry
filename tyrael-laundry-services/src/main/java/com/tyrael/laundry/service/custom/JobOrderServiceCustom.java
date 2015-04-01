package com.tyrael.laundry.service.custom;

import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
public interface JobOrderServiceCustom extends TyraelJpaService<JobOrder, JobOrderInfo> {

    PageInfo<JobOrderInfo> pageInfo(String term, PageRequest pageRequest);

}
