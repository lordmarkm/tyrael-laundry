package com.tyrael.laundry.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyrael.laundry.model.Customer;

public interface CustomerService extends JpaRepository<Customer, Long> {

}
