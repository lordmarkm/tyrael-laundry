package com.tyrael.laundry.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.tyrael.laundry.reference.ServiceType;

/**
 * @author mbmartinez
 */
@Entity(name = "JOB_SERVICE")
public class JobService {

    @Column(name = "SERVICE_TYPE")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "WT_KG")
    private BigDecimal weightInKilos;

    @Column(name = "PRICE_KG")
    private BigDecimal pricePerKilo;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
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
