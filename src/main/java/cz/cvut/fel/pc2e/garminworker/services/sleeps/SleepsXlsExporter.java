package cz.cvut.fel.pc2e.garminworker.services.sleeps;

import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepsXlsRowDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.DeviceService;
import cz.cvut.fel.pc2e.garminworker.services.TimeComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SleepsXlsExporter {
    private final DeviceService deviceService;
    private final TimeComponent timeComponent;

    @Autowired
    public SleepsXlsExporter(DeviceService deviceService, TimeComponent timeComponent) {
        this.deviceService = deviceService;
        this.timeComponent = timeComponent;
    }

    /**
     * @param dtos - ordered sleep summaries by date and grouped by user access token
     * @return xls file
     */
    public File getSleepsXls(List<SleepSummary> dtos) {
        List<SleepsXlsRowDto> xlsSleepDtos = this.convertEntitiesToXlsDto(dtos);
        return exportToXls(
                xlsSleepDtos
        );
    }

    /**
     * This method converts Sleep Summary business object as saved in DB to
     * a format that is easily readable to the user
     */
    private List<SleepsXlsRowDto> convertEntitiesToXlsDto(List<SleepSummary> sleepSummaryList) {
        List<SleepsXlsRowDto> res = new ArrayList<>();

        for (SleepSummary s : sleepSummaryList) {
            SleepsXlsRowDto sleepXlsRow = new SleepsXlsRowDto();

            // get research number and see if it exists
            String researchNumber = deviceService
                    .getAllResearchIdsByOauthToken(
                            s.getUserAccessToken()
                    );
            if (researchNumber == null) {
                log.debug("Didnt find any researh number for {}, skipping SleepSummary", s.getUserAccessToken());
                continue;
            }
            sleepXlsRow.setResearchNumber(
                    researchNumber
            );
            sleepXlsRow.setSummaryId(
                    s.getSummaryId()
            );
            sleepXlsRow.setResearchNumber(
                    researchNumber
            );
            sleepXlsRow.setCalendarDate(
                    timeComponent.reformatCalendarDate(s.getCalendarDate())
            );
            sleepXlsRow.setStartTime(
                    timeComponent.unixTimeToHours(
                            s.getStartTimeInSeconds()
                    )
            );

            Long startTime = s.getStartTimeInSeconds();
            Integer duration = s.getDurationInSeconds();
            Integer awakeTime = s.getAwakeDurationInSeconds();
            Long endTime = startTime + duration + awakeTime;
            sleepXlsRow.setEndTime(
                    timeComponent.unixTimeToHours(
                            endTime
                    )
            );

            sleepXlsRow.setDuration(
                    timeComponent.secondsToHourMinuteFormat(
                            s.getDurationInSeconds()
                    )
            );
            sleepXlsRow.setDeepSleepDuration(
                    timeComponent.secondsToHourMinuteFormat(
                            s.getDeepSleepDurationInSeconds()
                    )
            );
            sleepXlsRow.setLightSleepDuration(
                    timeComponent.secondsToHourMinuteFormat(
                            s.getLightSleepDurationInSeconds()
                    )
            );
            sleepXlsRow.setRemSleepDuration(
                    timeComponent.secondsToHourMinuteFormat(
                            s.getRemSleepInSeconds()
                    )
            );
            sleepXlsRow.setAwakeDuration(
                    timeComponent.secondsToHourMinuteFormat(
                            s.getAwakeDurationInSeconds()
                    )
            );
            sleepXlsRow.setUnmeasurableSleepDuration(
                    timeComponent.secondsToHourMinuteFormat(
                            s.getUnmeasurableSleepInSeconds()
                    )
            );
            sleepXlsRow.setValidation(s.getValidation());

            res.add(sleepXlsRow);
        }

        return res;
    }

    private File exportToXls(List<SleepsXlsRowDto> sleepXlsRowDto) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        // styles
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // font
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 11);
        font.setBold(true);
        headerStyle.setFont(font);

        CellStyle style = workbook.createCellStyle();

        Row row;

        // each sheet
        Sheet sheet = null;
        Row header;
        boolean diffResearchNumeber = true;
        int rowIndex = 0;

        for (int i = 0; i < sleepXlsRowDto.size(); i++) {
            SleepsXlsRowDto e = sleepXlsRowDto.get(i);

            if (diffResearchNumeber) {
                // create new sheet
                sheet = workbook.createSheet(e.getResearchNumber());
                sheet.setDefaultColumnWidth(20);
                header = sheet.createRow(0);
                createHeaderRow(header, headerStyle);
                rowIndex = 0;
            }

            rowIndex++;
            row = sheet.createRow(rowIndex);

            putValuesIntoRow(style, row, e);

            // the operations out of this would otherwise cause out of bounds error
            if (i + 1 >= sleepXlsRowDto.size()) {
                break;
            }

            String nextReseachNumber = sleepXlsRowDto.get(i + 1).getResearchNumber();
            String currentReseachNumber = e.getResearchNumber();
            diffResearchNumeber = !currentReseachNumber.equals(nextReseachNumber);
        }
        try {
            File file = File.createTempFile("templ", ".xlsx");
            try (FileOutputStream outputStream = new FileOutputStream(file)){
                workbook.write(outputStream);
                workbook.close();
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void putValuesIntoRow(CellStyle style, Row row, SleepsXlsRowDto e) {
        Cell cell;
        cell = row.createCell(0);
        cell.setCellValue(e.getSummaryId());
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(e.getResearchNumber());
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue(e.getCalendarDate());
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue(e.getStartTime());
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue(e.getEndTime());
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue(e.getDuration());
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue(e.getUnmeasurableSleepDuration());
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue(e.getAwakeDuration());
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue(e.getRemSleepDuration());
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue(e.getLightSleepDuration());
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue(e.getDeepSleepDuration());
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue(e.getValidation().toString());
        cell.setCellStyle(style);
    }

    private void createHeaderRow(Row header, CellStyle headerStyle) {
        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Id zaznamu");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Výzkumné číslo");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Datum");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Začátek spánku");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Konec spánku");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("Délka spánku");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(6);
        headerCell.setCellValue("Neměřitelná doba spánku");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(7);
        headerCell.setCellValue("Awake time");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(8);
        headerCell.setCellValue("REM");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(9);
        headerCell.setCellValue("Lehký spánek");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(10);
        headerCell.setCellValue("Hluboký spánek");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(11);
        headerCell.setCellValue("Validace");
        headerCell.setCellStyle(headerStyle);
    }
}
