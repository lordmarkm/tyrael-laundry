package com.tyrael.laundry.service.custom;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.Customer;
import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
@Transactional
public interface CustomerServiceCustom extends TyraelJpaServiceCustom<Customer, CustomerInfo> {

    PageInfo<CustomerInfo> pageInfo(String term, PageRequest pageRequest);

}
