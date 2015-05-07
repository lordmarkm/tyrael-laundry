package com.tyrael.laundry.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.tyrael.laundry.model.TransportRequest;

/**
 * @author mbmartinez
 */
@NoRepositoryBean
public interface TransportRequestService<T extends TransportRequest> {

}
