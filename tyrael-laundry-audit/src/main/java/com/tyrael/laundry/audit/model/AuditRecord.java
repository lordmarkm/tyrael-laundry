package com.tyrael.laundry.audit.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.baldy.commons.models.BaseEntity;
import com.tyrael.laundry.audit.reference.AuditRecordType;

@MappedSuperclass
public abstract class AuditRecord extends BaseEntity {

    @Column(name = "AR_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuditRecordType type;

    @Column(name = "MSG", nullable = false)
    private String message;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "EVT_TIME", nullable = false)
    private DateTime time;

    public AuditRecordType getType() {
        return type;
    }

    public void setType(AuditRecordType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

}
