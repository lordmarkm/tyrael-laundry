package com.tyrael.laundry.messaging.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.tyrael.laundry.messaging.TyraelLaundryMessagingMarker;

@Configuration
@ComponentScan(basePackageClasses = TyraelLaundryMessagingMarker.class)
public class TyraelLaundryMessagingConfig {

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate();
    }

    public ConnectionFactory connectionFactory() {
        ConnectionFactory c = new ActiveMQConnectionFactory("tcp://localhost:61616");
        return c;
    }
}
