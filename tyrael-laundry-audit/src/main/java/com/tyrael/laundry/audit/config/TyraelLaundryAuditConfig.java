package com.tyrael.laundry.audit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.tyrael.laundry.audit")
@EnableAspectJAutoProxy
@EnableJpaRepositories(
    basePackages = "com.tyrael.laundry.audit.service",
    repositoryImplementationPostfix = "CustomImpl"
)
public class TyraelLaundryAuditConfig {

}
