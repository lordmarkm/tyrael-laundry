package com.tyrael.laundry.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.tyrael.laundry.security.method.CustomMethodSecurityExpressionHandler;

/**
 * @author mbmartinez
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TyraelLaundryMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private CustomMethodSecurityExpressionHandler eh;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return eh;
    }
}
