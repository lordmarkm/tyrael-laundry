package com.tyrael.laundry.security.aspect;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.security.model.CustomerAccount;
import com.tyrael.laundry.security.model.JobOrderRegistrationCode;
import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.security.service.JobOrderRegistrationCodeService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.CustomerInfo;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * The job of this class is to create a new JobOrderRegistrationCode whenever a new JobOrder is created
 * that does not belong to a registrered user. This will create a registration code that the user can use
 * to register an account and claim the job order.
 * @author mbmartinez
 */
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class JobOrderRegistrationCodeCreationAspect {

    private static Logger LOG = LoggerFactory.getLogger(JobOrderRegistrationCodeCreationAspect.class);

    @Autowired
    private JobOrderRegistrationCodeService jobOrderRegistrationCodeService;

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostConstruct
    public void init() {
        LOG.debug("Initialized.");
    }

    @Around("execution(* com.tyrael.laundry.service.JobOrderService.saveInfo(..))")
    public Object saveInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        JobOrderInfo jobOrderToSave = (JobOrderInfo) joinPoint.getArgs()[0];
        JobOrderInfo savedJobOrder = (JobOrderInfo) joinPoint.proceed();

        //Proceed only if new job order (null id)
        if (null == jobOrderToSave.getId()) {

            CustomerInfo customerInfo = jobOrderToSave.getCustomer();
            CustomerAccount customerAccount = customerAccountService.findByCustomerId(customerInfo.getId());

            //Proceed only if unclaimed job order (owning customer has no registered account)
            if (null == customerAccount) {
                JobOrder jobOrder = jobOrderService.findByTrackingNo(savedJobOrder.getTrackingNo());

                JobOrderRegistrationCode jobOrderRegistrationCode = new JobOrderRegistrationCode();
                jobOrderRegistrationCode.setRegistrationCode(RandomStringUtils.randomAlphanumeric(4).toLowerCase());
                jobOrderRegistrationCode.setJobOrder(jobOrder);
                jobOrderRegistrationCodeService.save(jobOrderRegistrationCode);
            } else {
                LOG.debug("Customer is registered. Ignoring save event of unclaimiable job order.");
            }
        } else {
            LOG.debug("Tracking number is not null. Ignoring save event of existing job order.");
        }

        return savedJobOrder;
    }

}
