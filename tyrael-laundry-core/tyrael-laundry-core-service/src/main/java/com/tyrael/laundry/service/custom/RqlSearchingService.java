package com.tyrael.laundry.service.custom;

import org.springframework.data.domain.Pageable;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.commons.dto.PageInfo;

/**
 * @author mbmartinez
 */
public interface RqlSearchingService<E extends BaseEntity, D extends BaseTyraelDto> {

    PageInfo<D> searchRql(String term, Pageable pageRequest);

}
