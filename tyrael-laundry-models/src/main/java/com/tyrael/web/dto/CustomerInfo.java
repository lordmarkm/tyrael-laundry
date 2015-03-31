package com.tyrael.web.dto;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.AddressInfo;
import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.commons.dto.NameInfo;

/**
 * @author mbmartinez
 */
public class CustomerInfo extends BaseTyraelDto {

    private NameInfo name;
    private AddressInfo address;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("name", name)
            .append("address", address);
    }

    public NameInfo getName() {
        return name;
    }

    public void setName(NameInfo name) {
        this.name = name;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

}
