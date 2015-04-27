package com.tyrael.laundry.service.custom;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tyrael.web.dto.ServiceTypeInfo;

/**
 * @author mbmartinez
 */
public interface ServiceTypeServiceCustom {

    List<ServiceTypeInfo> findByEnabledInfo();

}
