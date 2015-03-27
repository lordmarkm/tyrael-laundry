package com.tyrael.landry.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.tyrael.laundry.app")
@EnableTransactionManagement
public class TyraelLaundryAppConfig {

}
