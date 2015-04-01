package com.tyrael.laundry.service.custom.impl;

import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.service.CustomerService;
import com.tyrael.laundry.service.custom.CustomerServiceCustom;
import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
public class CustomerServiceCustomImpl extends TyraelJpaServiceCustomImpl<Customer, CustomerInfo, CustomerService>
    implements CustomerServiceCustom {

    @Override
    public PageInfo<CustomerInfo> pageInfo(String term, PageRequest pageRequest) {
        return super.pageInfo(pageRequest);
    }

}
