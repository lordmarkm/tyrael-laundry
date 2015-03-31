package com.tyrael.laundry.service.custom;

import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.dto.PageInfo;
import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
public interface CustomerServiceCustom {

    PageInfo<CustomerInfo> pageInfo(String term, PageRequest pageRequest);

}
