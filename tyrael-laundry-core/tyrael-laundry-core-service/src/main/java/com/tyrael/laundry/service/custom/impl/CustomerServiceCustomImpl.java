package com.tyrael.laundry.service.custom.impl;

import static com.tyrael.laundry.model.QCustomer.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;

import com.mysema.query.types.Predicate;
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

    private static Logger LOG = LoggerFactory.getLogger(CustomerServiceCustomImpl.class);

    @Override
    public PageInfo<CustomerInfo> pageInfo(String term, PageRequest pageRequest) {
        LOG.debug("Searching customers. term={}", term);

        Predicate nameSearch = customer.name.surname.startsWithIgnoreCase(term)
            .or(customer.name.surname.startsWithIgnoreCase(term))
            .or(customer.name.middleName.startsWithIgnoreCase(term));

        return super.pageInfo(nameSearch, pageRequest);
    }

}
