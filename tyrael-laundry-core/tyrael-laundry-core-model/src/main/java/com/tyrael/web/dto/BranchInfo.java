package com.tyrael.web.dto;

import org.springframework.core.style.ToStringCreator;

public class BranchInfo {

    private int newTransportRequests;
    private int newJobOrders;

    public String toString() {
        return new ToStringCreator(this)
            .append("new transport rqs", newTransportRequests)
            .append("new job orders", newJobOrders)
            .toString();
    }

    public int getNewTransportRequests() {
        return newTransportRequests;
    }

    public void setNewTransportRequests(int newTransportRequests) {
        this.newTransportRequests = newTransportRequests;
    }

    public int getNewJobOrders() {
        return newJobOrders;
    }

    public void setNewJobOrders(int newJobOrders) {
        this.newJobOrders = newJobOrders;
    }
    

}
