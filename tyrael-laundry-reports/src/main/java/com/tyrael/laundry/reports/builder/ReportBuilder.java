package com.tyrael.laundry.reports.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateMidnight;

public interface ReportBuilder {

    String DATE_TIME_FORMAT = "MMM-dd-yyyy hh:mm a";
    String DATE_FORMAT = "MMM-dd-yyyy";
    String DATE_FORMAT_NO_YEAR = "MMM-dd";
    Workbook buildReport(DateMidnight datefrom, DateMidnight dateto);

}
