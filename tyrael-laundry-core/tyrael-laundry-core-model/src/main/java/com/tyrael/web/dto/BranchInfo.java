package com.tyrael.web.dto;

import java.math.BigDecimal;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.BaseTyraelNamedDto;

/**
 * @author mbmartinez
 */
public class BranchInfo extends BaseTyraelNamedDto {

    private int newTransportRequests;
    private int newJobOrders;
    private BigDecimal minimumJobOrderAmount;

    @Override
    public ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("new transport rqs", newTransportRequests)
            .append("new job orders", newJobOrders)
            .append("minimumJobOrderAmount", minimumJobOrderAmount);
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

    public BigDecimal getMinimumJobOrderAmount() {
        return minimumJobOrderAmount;
    }

    public void setMinimumJobOrderAmount(BigDecimal minimumJobOrderAmount) {
        this.minimumJobOrderAmount = minimumJobOrderAmount;
    }
    

}
