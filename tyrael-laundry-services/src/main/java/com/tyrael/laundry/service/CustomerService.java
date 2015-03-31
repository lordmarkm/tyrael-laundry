package com.tyrael.laundry.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.service.custom.CustomerServiceCustom;

/**
 * @author mbmartinez
 */
public interface CustomerService extends CustomerServiceCustom, JpaRepository<Customer, Long> {

}
