package com.tyrael.laundry.service.custom.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobItem;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.model.JobService;
import com.tyrael.laundry.model.QJobOrder;
import com.tyrael.laundry.reference.JobOrderStatus;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.laundry.service.TyraelLaundryJobOrderSequenceService;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;
import com.tyrael.web.dto.JobOrderInfo;
import static com.tyrael.laundry.model.QJobOrder.jobOrder;

/**
 * @author mbmartinez
 */
@Transactional
public class JobOrderServiceCustomImpl extends TyraelJpaServiceCustomImpl<JobOrder, JobOrderInfo, JobOrderService>
    implements JobOrderServiceCustom  {

    @Autowired
    private TyraelLaundryJobOrderSequenceService sequenceService;

    @Override
    public PageInfo<JobOrderInfo> pageInfo(String term, String status, PageRequest pageRequest) {
        //Name filter
        BooleanExpression predicate = jobOrder.customer.name.surname.containsIgnoreCase(term)
                .or(jobOrder.customer.name.givenName.containsIgnoreCase(term));

        //trackingNo filter
        predicate = predicate.or(jobOrder.trackingNo.eq(term));

        //Status filter
        if (null != status && status.length() > 0) {
            predicate = predicate.and(jobOrder.status.eq(JobOrderStatus.valueOf(status)));
        }

        return super.pageInfo(predicate, pageRequest);
    }

    @Override
    public JobOrderInfo findByTrackinNoInfo(String trackingNo) {
        return toDto(repo.findByTrackingNo(trackingNo));
    }

    @Override
    public JobOrderInfo saveInfo(JobOrderInfo jobOrderInfo) {
        if (null == jobOrderInfo.getTrackingNo()) {
            jobOrderInfo.setDateReceived(DateTime.now());
            jobOrderInfo.setDateDue(jobOrderInfo.getDateReceived().plusDays(3));
            jobOrderInfo.setTrackingNo(sequenceService.next());
        }

        JobOrder jobOrder = toEntity(jobOrderInfo);
        for (JobService service : jobOrder.getJobServices()) {
            service.setJobOrder(jobOrder);
        }
        for (JobItem item : jobOrder.getJobItems()) {
            item.setJobOrder(jobOrder);
        }

        return toDto(repo.saveAndFlush(jobOrder));
    }

}
