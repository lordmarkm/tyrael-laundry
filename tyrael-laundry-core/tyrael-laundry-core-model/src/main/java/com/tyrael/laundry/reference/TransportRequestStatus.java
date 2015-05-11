package com.tyrael.laundry.reference;

/**
 * @author mbmartinez
 */
public enum TransportRequestStatus {

    NEW("New"),
    QUEUED("Queued"),
    IN_TRANSIT("In transit"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    ADDR_NOT_FOUND("Address not found"),
    ADDR_INVALID("Address invalid or incomplete");

    private String label;
    private TransportRequestStatus(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}
