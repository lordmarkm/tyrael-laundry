package com.tyrael.web.dto;

import java.math.BigDecimal;

import com.tyrael.commons.dto.BaseTyraelDto;

/**
 * @author mbmartinez
 */
public class ServiceTypeInfo extends BaseTyraelDto {

    private String code;
    private String label;
    private String icon;
    private boolean enabled;
    private BigDecimal pricePerKilo;

    public String toString() {
        return super.toStringCreator()
                .append("code", code)
                .append("label", label)
                .append("icon", icon)
                .append("enabled", enabled)
                .append("price/kilo", pricePerKilo)
                .toString();
    }

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
