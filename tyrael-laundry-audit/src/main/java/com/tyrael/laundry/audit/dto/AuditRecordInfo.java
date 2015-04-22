package com.tyrael.laundry.audit.dto;

import org.joda.time.DateTime;

import com.tyrael.laundry.audit.reference.AuditRecordType;

/**
 * @author mbmartinez
 */
public class AuditRecordInfo {

    private AuditRecordType type;
    private String message;
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
