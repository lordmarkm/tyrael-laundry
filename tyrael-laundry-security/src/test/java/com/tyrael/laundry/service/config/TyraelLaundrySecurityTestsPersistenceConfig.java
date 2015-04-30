package com.tyrael.laundry.service.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tyrael.commons.data.config.PersistenceConfig;

/**
 * @author Mark
 */
@Configuration
@PropertySource({"classpath:db.properties"})
public class TyraelLaundrySecurityTestsPersistenceConfig extends PersistenceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(TyraelLaundrySecurityTestsPersistenceConfig.class);

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        LOG.debug("Persistence Configuration loaded.");
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("javax.persistence.validation.mode", "NONE");//disable as it overrides model attrib validators
        return properties;
    }

    @Override
    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jpa.driverClass"));
        dataSource.setJdbcUrl(env.getProperty("jpa.url"));
        dataSource.setUser(env.getProperty("jpa.username"));
        dataSource.setPassword(env.getProperty("jpa.password"));

        //c3p0-specific properties follow
        dataSource.setAcquireIncrement(1);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(5);
        dataSource.setMaxIdleTime(600);

        return dataSource;
    }

    @Override
    @Bean
    public EntityManagerFactory entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(new String[] {
            "com.baldy.commons.security.models",
            "com.tyrael.laundry.model",
            "com.tyrael.laundry.sequence",
            "com.tyrael.laundry.*.model"
        });
        entityManagerFactory.setPersistenceProvider(new HibernatePersistence());
        entityManagerFactory.setJpaProperties(hibernateProperties());
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

}
