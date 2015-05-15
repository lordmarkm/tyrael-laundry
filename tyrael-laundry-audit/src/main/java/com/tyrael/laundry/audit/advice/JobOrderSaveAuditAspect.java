package com.tyrael.laundry.audit.advice;

import static com.tyrael.laundry.audit.reference.AuditRecordType.CREATE;
import static com.tyrael.laundry.audit.reference.AuditRecordType.FINALIZE;
import static com.tyrael.laundry.audit.reference.AuditRecordType.UPDATE;
import static com.tyrael.laundry.reference.JobOrderStatus.CANCELLED;
import static com.tyrael.laundry.reference.JobOrderStatus.CLOSED;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.audit.model.JobOrderAuditRecord;
import com.tyrael.laundry.audit.service.JobOrderAuditRecordService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.JobOrderInfo;

@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class JobOrderSaveAuditAspect {

    @Autowired
    private JobOrderAuditRecordService service;

    @Autowired
    private JobOrderService jobOrderService;

    @Around("execution(* com.tyrael.laundry.service.JobOrderService.saveInfo(..))")
    public Object saveInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        JobOrderInfo toSave = (JobOrderInfo) joinPoint.getArgs()[0];
        JobOrderInfo saved = (JobOrderInfo) joinPoint.proceed();

        JobOrderAuditRecord record = new JobOrderAuditRecord();
        record.setTime(DateTime.now());
        record.setJobOrder(jobOrderService.findOne(saved.getId()));

        if (toSave.getId() == null) {
            record.setType(CREATE);
            record.setMessage(getUsername() + " created job record");
        } else if (toSave.getStatus() == CLOSED || toSave.getStatus() == CANCELLED) {
            record.setType(FINALIZE);
            record.setMessage(getUsername() + " finalized job record. Status is " + saved.getStatus());
        } else {
            record.setType(UPDATE);
            record.setMessage(getUsername() + " updated job record. Status is " + saved.getStatus());
        }

        service.save(record);

        return saved;
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getClass().isAssignableFrom(User.class)) {
            return ((User) principal).getUsername();
        } else {
            return "Anonymous";
        }
    }
}
