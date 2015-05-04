package com.tyrael.laundry.security.config;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tyrael.laundry.security.dto.CustomerAccountInfo;
import com.tyrael.laundry.security.model.CustomerAccount;

@Configuration
public class TyraelLaundrySecuritySupportConfig {

    @Autowired
    private DozerBeanMapper mapper;

    @PostConstruct
    public void init() {
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
//                mapping(CustomerAccount.class, CustomerAccountInfo.class)
//                    .fields("customer", "customer")
//                    .fields("account", "account");
            }
        });
    }

}
