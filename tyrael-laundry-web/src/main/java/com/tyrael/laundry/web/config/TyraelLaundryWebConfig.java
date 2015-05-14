package com.tyrael.laundry.web.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tyrael.laundry.app.config.TyraelLaundryAppConfigMarker;
import com.tyrael.laundry.audit.config.TyraelLaundryAuditConfigMarker;
import com.tyrael.laundry.billing.config.TyraelLaundryBillingConfigMarker;
import com.tyrael.laundry.security.config.TyraelLaundrySecurityConfigMarker;
import com.tyrael.laundry.web.TyraelLaundryWebMarker;

@Configuration
@ComponentScan(basePackages = {
    "com.baldy.commons.web.config",
    "com.baldy.commons.resourcedoc.config"
}, basePackageClasses = {
    TyraelLaundryAppConfigMarker.class,
    TyraelLaundrySecurityConfigMarker.class,
    TyraelLaundryBillingConfigMarker.class,
    TyraelLaundryAuditConfigMarker.class,
    TyraelLaundryWebMarker.class
})
@PropertySource({"classpath:app.properties"})
@EnableAspectJAutoProxy
public class TyraelLaundryWebConfig extends WebMvcConfigurationSupport {

    //Enable direct access to .html, .css, etc
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(); 
    }

    //Allows us to use Pageable as an argument for controller methods
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setMaxPageSize(100);
        resolver.setFallbackPageable(new PageRequest(0, 100));
        resolver.setOneIndexedParameters(true);
        resolver.setSizeParameterName("count");
        argumentResolvers.add(resolver);
    }

    //Override fail on unknown properties
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.addDefaultHttpMessageConverters(converters);
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ((MappingJackson2HttpMessageConverter) converter)
                    .getObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .registerModule(new JodaModule())
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            }
        }
    }
}
