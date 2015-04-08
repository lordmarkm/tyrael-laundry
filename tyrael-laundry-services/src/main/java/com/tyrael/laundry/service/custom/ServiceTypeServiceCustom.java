package com.tyrael.laundry.service.custom;

import java.util.List;

import com.tyrael.web.dto.ServiceTypeInfo;

/**
 * @author mbmartinez
 */
public interface ServiceTypeServiceCustom {

    List<ServiceTypeInfo> findByEnabledInfo();

}
