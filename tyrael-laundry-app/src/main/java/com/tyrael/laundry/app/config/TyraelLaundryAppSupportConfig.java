package com.tyrael.laundry.app.config;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.tyrael.commons.mapper.config.MapperConfig;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.web.dto.JobOrderInfo;

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
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(JobOrderInfo.class, JobOrder.class)
                    .fields("dateReceived", "dateReceived", copyByReference())
                    .fields("dateDue", "dateDue", copyByReference());
            }
        });
    }

}
