package com.tyrael.laundry.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tyrael.laundry.service.JobOrderService;
import static org.mockito.Mockito.*;

@Configuration
public class TyraelLaundrySecurityTestsConfig {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public JobOrderService jobOrderService() {
        return mock(JobOrderService.class);
    }
}
