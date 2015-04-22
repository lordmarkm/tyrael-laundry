package com.tyrael.laundry.audit.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.audit.model.JobOrderAuditRecord;

public interface JobOrderAuditRecordService extends JobOrderAuditRecordServiceCustom, TyraelJpaService<JobOrderAuditRecord> {

    List<JobOrderAuditRecord> findByJobOrder_TrackingNo(String jobOrderTrackingNo, Pageable page);

}
