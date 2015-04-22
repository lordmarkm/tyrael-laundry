package com.tyrael.laundry.audit.config;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.tyrael.laundry.audit.dto.AuditRecordInfo;
import com.tyrael.laundry.audit.model.AuditRecord;

@Configuration
public class TyraelLaundryAuditSupportConfig {

    @Autowired
    private DozerBeanMapper mapper;

    @PostConstruct
    public void init() {
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(AuditRecord.class, AuditRecordInfo.class)
                    .fields("time", "time", copyByReference());
            }
        });
    }
}
