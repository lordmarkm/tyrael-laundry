package com.tyrael.laundry.service.custom;

import java.util.List;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.model.ServiceType;
import com.tyrael.web.dto.ServiceTypeInfo;

/**
 * @author mbmartinez
 */
public interface ServiceTypeServiceCustom extends TyraelJpaServiceCustom<ServiceType, ServiceTypeInfo> {

    List<ServiceTypeInfo> findByEnabledInfo();

}
