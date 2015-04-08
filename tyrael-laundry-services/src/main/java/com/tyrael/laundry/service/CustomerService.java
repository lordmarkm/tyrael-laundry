package com.tyrael.laundry.service;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.service.custom.CustomerServiceCustom;

/**
 * @author mbmartinez
 */
public interface CustomerService extends CustomerServiceCustom, TyraelJpaService<Customer> {

}
