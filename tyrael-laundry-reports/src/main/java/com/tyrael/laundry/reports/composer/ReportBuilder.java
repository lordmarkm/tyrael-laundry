package com.tyrael.laundry.reports.composer;

import org.apache.poi.ss.usermodel.Workbook;

public interface ReportBuilder {

    String DATE_TIME_FORMAT = "MMM-dd-yyyy hh:mm a";
    Workbook buildReport();

}
