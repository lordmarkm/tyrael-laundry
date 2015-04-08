package com.tyrael.laundry.service.custom.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobItem;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.model.JobService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.laundry.service.TyraelLaundryJobOrderSequenceService;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;
import com.tyrael.web.dto.JobOrderInfo;

/**
 * @author mbmartinez
 */
public class JobOrderServiceCustomImpl extends TyraelJpaServiceCustomImpl<JobOrder, JobOrderInfo, JobOrderService>
    implements JobOrderServiceCustom  {

    @Autowired
    private TyraelLaundryJobOrderSequenceService sequenceService;

    @Override
    public PageInfo<JobOrderInfo> pageInfo(String term, PageRequest pageRequest) {
        return super.pageInfo(pageRequest);
    }

    @Override
    public JobOrderInfo findByTrackinNoInfo(String trackingNo) {
        return toDto(repo.findByTrackingNo(trackingNo));
    }

    @Override
    public JobOrderInfo saveInfo(JobOrderInfo jobOrderInfo) {
        jobOrderInfo.setDateReceived(DateTime.now());
        jobOrderInfo.setDateDue(jobOrderInfo.getDateReceived().plusDays(3));
        jobOrderInfo.setTrackingNo(sequenceService.next());

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
