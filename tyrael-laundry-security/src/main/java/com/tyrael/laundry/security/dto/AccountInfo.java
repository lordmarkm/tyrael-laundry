package com.tyrael.laundry.security.dto;

import org.springframework.core.style.ToStringCreator;

import com.tyrael.commons.dto.BaseTyraelDto;

/**
 * @author mbmartinez
 */
public class AccountInfo extends BaseTyraelDto {

    private String username;

    @Override
    protected ToStringCreator toStringCreator() {
        return super.toStringCreator()
            .append("username", username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
