package com.tyrael.laundry.reports.builder;

import static com.tyrael.laundry.model.QJobOrder.jobOrder;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.MutableDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.tyrael.laundry.service.JobOrderService;

@Component
public class SummaryReportBuilder implements ReportBuilder {

    private static Logger LOG = LoggerFactory.getLogger(SummaryReportBuilder.class);

    @Autowired
    private JobOrderService service;

    private DateMidnight firstDayOfMonth() {
        MutableDateTime mdt = MutableDateTime.now();
        mdt.setDayOfMonth(1);
        return new DateMidnight(mdt);
    }

    @Override
    public Workbook buildReport(DateMidnight datefrom, DateMidnight dateto) {

        DateMidnight start = datefrom != null ? datefrom : firstDayOfMonth();
        DateMidnight end = dateto != null ? dateto : DateMidnight.now();
        List<DateMidnight> dates = getDays(start, end);
        LOG.debug("start={}, end={}, days={}", start, end, dates.size());

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Job orders");

        Row row;
        Cell cell;

        //Titles row
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("Date");
        row.createCell(1).setCellValue("Received");
        row.createCell(2).setCellValue("Completed");

        for (int rowIndex = 0; rowIndex < dates.size(); rowIndex++) {
            DateMidnight date = dates.get(rowIndex);
            row = sheet.createRow(rowIndex + 1);
            long received = service.count(
                        jobOrder.dateReceived.dayOfYear().eq(date.getDayOfYear())
                            .and(jobOrder.dateReceived.year().eq(date.getYear()))
                    );
            long completed = service.count(
                        jobOrder.dateCompleted.dayOfYear().eq(date.getDayOfYear())
                            .and(jobOrder.dateCompleted.year().eq(date.getYear()))
                    );
            
            cell = row.createCell(0);
            cell.setCellValue(date.toString(DATE_FORMAT));
            cell = row.createCell(1);
            cell.setCellValue(received);
            cell = row.createCell(2);
            cell.setCellValue(completed);
        }

        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 4, 0, 14, 15);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        // Use a category axis for the bottom axis.
        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, dates.size(), 0, 0));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, dates.size(), 1, 1));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, dates.size(), 2, 2));


        LineChartSeries received = data.addSeries(xs, ys1);
        received.setTitle("Job orders received.");
        LineChartSeries completed = data.addSeries(xs, ys2);
        completed.setTitle("Job orders completed.");

        chart.plot(data, bottomAxis, leftAxis);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        return wb;
    }

    private List<DateMidnight> getDays(DateMidnight start, DateMidnight end) {
        List<DateMidnight> dates = Lists.newArrayList();
        int days = Days.daysBetween(start, end).getDays()+1;
        for (int i=0; i < days; i++) {
            DateMidnight d = start.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        return dates;
    }

    public static void main(String args[]) {
        SummaryReportBuilder s = new SummaryReportBuilder();
        DateMidnight today = DateMidnight.now();
        DateMidnight oneMonthAgo = DateMidnight.now().minusMonths(1);
        System.out.println(s.getDays(oneMonthAgo, today));
    }
}
