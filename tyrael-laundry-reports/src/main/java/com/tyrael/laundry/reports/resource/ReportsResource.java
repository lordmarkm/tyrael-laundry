package com.tyrael.laundry.reports.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.reports.builder.ReportDelegator;

@RestController
@RequestMapping("/reports")
public class ReportsResource {

    @Autowired
    private ReportDelegator composer;

    @RequestMapping(method = RequestMethod.GET)
    public void getReport(HttpServletResponse response) throws IOException {
//        Workbook wb = composer.getReport();
        Workbook wb = composer.getLineChart();
        response.setHeader("Content-disposition", "attachment; filename=test.xls");
        wb.write(response.getOutputStream());
    }
}
