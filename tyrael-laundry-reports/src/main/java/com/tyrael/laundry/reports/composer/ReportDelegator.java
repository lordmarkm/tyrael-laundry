package com.tyrael.laundry.reports.composer;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportDelegator {

    @Autowired
    private JobOrderReportBuilder jobOrderReportBuilder;

    public Workbook getReport() {
        return jobOrderReportBuilder.buildReport();
    }

}
