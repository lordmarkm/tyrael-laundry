package com.tyrael.laundry.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({
    "com.tyrael.laundry.app",
    "com.tyrael.laundry.service"
})
@EnableTransactionManagement
public class TyraelLaundryAppConfig {

}
