package com.tyrael.laundry.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.baldy.commons.models.BaseEntity;

/**
 * @author mbmartinez
 */
@Entity(name = "REF_SERVICETYPE")
public class ServiceType extends BaseEntity {

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "LABEL", nullable = false)
    private String label;

    @Column(name = "ICON", nullable = false)
    private String icon;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

    @Column(name = "PRICE_PER_KILO", nullable = false)
    private BigDecimal pricePerKilo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public BigDecimal getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
