package com.tyrael.laundry.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.model.Customer;

/**
 * @author mbmartinez
 */
public interface CustomerService extends JpaRepository<Customer, Long> {

}
