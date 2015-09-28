package com.tyrael.laundry.service;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Query;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;

/**
 * @author mbmartinez
 */
public interface JobOrderService extends JobOrderServiceCustom, TyraelJpaService<JobOrder> {

    JobOrder findByTrackingNo(String trackingNo);
    JobOrder findByJobCode(String string);

    @Query("select customer.id from JOB_ORDER j where j.trackingNo=?1")
    Long getCustomerId(String trackingNo);

    List<JobOrder> findByCustomer(Customer customer);

    @Query("select sum(j.totalAmountPaid) from JOB_ORDER j where j.dateCompleted between ?1 and ?2")
    BigDecimal getIncomeTotal(DateTime start, DateTime end);

}
