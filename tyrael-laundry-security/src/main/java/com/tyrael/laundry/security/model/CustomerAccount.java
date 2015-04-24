package com.tyrael.laundry.security.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.baldy.commons.security.models.Account;
import com.tyrael.laundry.model.Customer;

@Entity(name = "CUSTOMER_ACCT")
public class CustomerAccount {

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false, unique = true)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "ACCT_ID", nullable = false, unique = true)
    private Account account;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
