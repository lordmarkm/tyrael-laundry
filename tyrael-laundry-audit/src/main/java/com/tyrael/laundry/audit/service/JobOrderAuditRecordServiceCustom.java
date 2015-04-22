package com.tyrael.laundry.audit.service;

import java.util.List;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.audit.dto.AuditRecordInfo;
import com.tyrael.laundry.audit.model.JobOrderAuditRecord;

public interface JobOrderAuditRecordServiceCustom 
    extends TyraelJpaServiceCustom<JobOrderAuditRecord, AuditRecordInfo>{

    List<AuditRecordInfo> findByJobOrderTrackingNoInfo(String jobOrderTrackingNo);

}
