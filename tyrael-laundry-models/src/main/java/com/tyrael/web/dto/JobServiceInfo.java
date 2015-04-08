package com.tyrael.web.dto;

import java.math.BigDecimal;

import com.tyrael.commons.dto.BaseTyraelDto;

public class JobServiceInfo extends BaseTyraelDto {

    private ServiceTypeInfo serviceType;
    private BigDecimal weightInKilos;
    private BigDecimal pricePerKilo;
    private BigDecimal amount;

    @Override
    public String toString() {
        return super.toStringCreator()
            .append("serviceType", serviceType)
            .append("weight", weightInKilos + " Kg")
            .append("price/kilo", pricePerKilo)
            .append("amount", amount)
            .toString();
    }

    public ServiceTypeInfo getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypeInfo serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getWeightInKilos() {
        return weightInKilos;
    }

    public void setWeightInKilos(BigDecimal weightInKilos) {
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
