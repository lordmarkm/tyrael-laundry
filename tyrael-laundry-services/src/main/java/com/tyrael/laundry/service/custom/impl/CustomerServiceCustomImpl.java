package com.tyrael.laundry.service.custom.impl;

import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.custom.CustomerServiceCustom;
import com.tyrael.web.dto.CustomerInfo;

public class CustomerServiceCustomImpl implements CustomerServiceCustom {

    @Override
    public PageInfo<CustomerInfo> pageInfo(String term, PageRequest pageRequest) {
        // TODO Auto-generated method stub
        return null;
    }

}
