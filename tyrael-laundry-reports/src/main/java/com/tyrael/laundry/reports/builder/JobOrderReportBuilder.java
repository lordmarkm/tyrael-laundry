package com.tyrael.laundry.reports.builder;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dozer.Mapper;
import org.joda.time.DateMidnight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.CustomerInfo;

@Component
public class JobOrderReportBuilder implements ReportBuilder {

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private Mapper mapper;

    public Workbook buildReport(DateMidnight datefrom, DateMidnight dateto) {
        boolean xlsx = true;
        Workbook wb = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Job orders");
        List<JobOrder> jobOrders = jobOrderService.findAll();
        for (int i = 0; i < jobOrders.size(); i++) {
            JobOrder jobOrder = jobOrders.get(i);
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(jobOrder.getTrackingNo());
            row.createCell(1).setCellValue(mapper.map(jobOrder.getCustomer(), CustomerInfo.class).getFormattedName());
            row.createCell(2).setCellValue(jobOrder.getDateReceived().toString(DATE_TIME_FORMAT));
            row.createCell(3).setCellValue(jobOrder.getStatus().name());
        }
        return wb;
    }
}
