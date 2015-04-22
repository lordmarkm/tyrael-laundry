package com.tyrael.laundry.audit.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.audit.dto.AuditRecordInfo;
import com.tyrael.laundry.audit.model.JobOrderAuditRecord;
import com.tyrael.laundry.audit.service.JobOrderAuditRecordService;
import com.tyrael.laundry.audit.service.JobOrderAuditRecordServiceCustom;

public class JobOrderAuditRecordServiceCustomImpl
    extends TyraelJpaServiceCustomImpl<JobOrderAuditRecord, AuditRecordInfo, JobOrderAuditRecordService>
    implements JobOrderAuditRecordServiceCustom {

    @Override
    public List<AuditRecordInfo> findByJobOrderTrackingNoInfo(String jobOrderTrackingNo) {

        Sort sort = new Sort(Direction.DESC, "time");
        PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE, sort);
        List<JobOrderAuditRecord> results = repo.findByJobOrder_TrackingNo(jobOrderTrackingNo, pageRequest);

        return toDto(results);
    }

}
