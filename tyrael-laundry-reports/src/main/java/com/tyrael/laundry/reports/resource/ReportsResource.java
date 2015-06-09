package com.tyrael.laundry.reports.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.reports.builder.ReportBuilder;
import com.tyrael.laundry.reports.builder.ReportDelegator;

@RestController
@RequestMapping("/reports")
public class ReportsResource {

    @Autowired
    private ReportDelegator composer;

    @RequestMapping(method = RequestMethod.GET)
    public void getReport(
            @RequestParam String reportName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMM-dd-yyyy") DateMidnight datefrom,
            @RequestParam(required = false) @DateTimeFormat(pattern = "MMM-dd-yyyy") DateMidnight dateto,
            HttpServletResponse response) throws IOException {
//        Workbook wb = composer.getReport();
        Workbook wb = composer.compseReport(reportName, datefrom, dateto);
        response.setHeader("Content-disposition", "attachment; filename=" + reportName + " " + datefrom.toString(ReportBuilder.DATE_FORMAT) 
                + " to " + dateto.toString(ReportBuilder.DATE_FORMAT) + ".xlsx");
        wb.write(response.getOutputStream());
    }
}
