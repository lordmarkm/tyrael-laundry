package com.tyrael.laundry.reports.builder;

import org.apache.poi.ss.usermodel.Workbook;
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

    public Workbook getReport() {
        return jobOrderReportBuilder.buildReport();
    }

    public Workbook getLineChart() {
        return summaryReportBuilder.buildReport();
    }
}
