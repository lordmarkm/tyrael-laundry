package com.tyrael.laundry.billing.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.tyrael.laundry.billing")
@EnableTransactionManagement
public class TyraelLaundryBillingConfig {

}
