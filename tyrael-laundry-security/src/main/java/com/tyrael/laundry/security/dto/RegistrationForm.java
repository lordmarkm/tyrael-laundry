package com.tyrael.laundry.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.style.ToStringCreator;

/**
 * @author mbmartinez
 */
public class RegistrationForm {

    @NotNull(message = "Registration code can't be empty")
    @Size(min = 4, max = 30, message = "Registration code length invalid")
    private String registrationCode;

    @NotNull(message = "Username is required")
    @Size(min = 6, max = 30, message = "Username must be between 6 and 30 characters in length")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 4, max = 30, message = "Password must be between 4 and 30 characters in length")
    private String password;

    @NotNull(message = "Password is required")
    @Size(min = 4, max = 30, message = "Password must be between 4 and 30 characters in length")
    private String confirmPassword;

    @Override
    public String toString() {
        return new ToStringCreator(this)
            .append("reg code", registrationCode)
            .append("un", username)
            .append("pw", StringUtils.isNotBlank(password) ? "Provided" : "Not provided")
            .append("confirm pw", StringUtils.isNotBlank(confirmPassword) ? "Provided" : "Not provided")
            .toString();
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
