package com.tyrael.laundry.app.config;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.tyrael.commons.mapper.config.MapperConfig;

/**
 * Initialize the Dozer mapper for application-wide use.
 * @author mbmartinez
 */
@Configuration
@Import(MapperConfig.class)
public class TyraelLaundryAppSupportConfig {

    @Autowired
    private DozerBeanMapper mapper;

    @PostConstruct
    public void init() {
        //Add custom mappings here
    }

}
