package com.tyrael.laundry.service;

import java.util.List;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.ServiceType;
import com.tyrael.laundry.service.custom.ServiceTypeServiceCustom;

/**
 * @author mbmartinez
 */
public interface ServiceTypeService extends TyraelJpaService<ServiceType>, ServiceTypeServiceCustom {

    ServiceType findByCode(String code);
    List<ServiceType> findByEnabled(boolean enabled);

}
