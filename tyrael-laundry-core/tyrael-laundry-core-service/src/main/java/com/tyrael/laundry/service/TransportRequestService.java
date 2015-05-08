package com.tyrael.laundry.service;

import org.springframework.data.repository.NoRepositoryBean;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.TransportRequest;

/**
 * @author mbmartinez
 */
@NoRepositoryBean
public interface TransportRequestService<T extends TransportRequest> extends TyraelJpaService<T> {

}
