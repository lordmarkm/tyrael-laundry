package com.tyrael.laundry.reports.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateMidnight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportDelegator {

    @Autowired
    private JobOrderReportBuilder jobOrderReportBuilder;

    @Autowired
    private LineChartTest lineChartTest;

    @Autowired
    private SummaryReportBuilder summaryReportBuilder;

    public Workbook compseReport(String reportName, DateMidnight datefrom, DateMidnight dateto) {
        switch (reportName) {
        case "Summary":
            return summaryReportBuilder.buildReport(datefrom, dateto);
        default:
            throw new IllegalArgumentException("Unrecognized report name: " + reportName);
        }
    }
}
