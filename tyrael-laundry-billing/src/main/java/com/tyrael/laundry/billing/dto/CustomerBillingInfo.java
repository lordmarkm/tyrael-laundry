package com.tyrael.laundry.billing.dto;

import java.math.BigDecimal;

import com.tyrael.web.dto.CustomerInfo;

/**
 * @author mbmartinez
 */
public class CustomerBillingInfo {

    private CustomerInfo customer;
    private int openJobOrders;
    private int closedJobOrders;
    private BigDecimal balance;
    private BigDecimal credit;

    public int getOpenJobOrders() {
        return openJobOrders;
    }
    public void setOpenJobOrders(int openJobOrders) {
        this.openJobOrders = openJobOrders;
    }
    public int getClosedJobOrders() {
        return closedJobOrders;
    }
    public void setClosedJobOrders(int closedJobOrders) {
        this.closedJobOrders = closedJobOrders;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public BigDecimal getCredit() {
        return credit;
    }
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
    public CustomerInfo getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

}
