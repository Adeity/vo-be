package cz.cvut.fel.vyzkumodolnosti.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.cvut.fel.vyzkumodolnosti.handler.EntryNotFoundException;
import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepsPushNotificationDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.vyzkumodolnosti.repository.sleep.SleepSummaryInfo;
import cz.cvut.fel.vyzkumodolnosti.repository.sleep.SleepSummaryJpaRepository;
import cz.cvut.fel.vyzkumodolnosti.services.sleeps.SleepsService;
import cz.cvut.fel.vyzkumodolnosti.services.sleeps.StringToSleepPushNotificationMapper;
import cz.cvut.fel.vyzkumodolnosti.xls.converters.EntityToXlsRowDtoConverter;
import cz.cvut.fel.vyzkumodolnosti.xls.dtos.XlsFileExporter;
import cz.cvut.fel.vyzkumodolnosti.xls.dtos.row.XlsRowDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/garmin/sleeps")
public class SleepsController {
    private final SleepsService sleepsService;
    private final SleepSummaryJpaRepository sleepSummaryJpaRepository;
    private final StringToSleepPushNotificationMapper stringToSleepPushNotificationMapper;
    private final XlsFileExporter xlsFileExporter = new XlsFileExporter();
    private final EntityToXlsRowDtoConverter<SleepSummary> entityToXlsRowDtoConverter;

    @Autowired
    public SleepsController(SleepsService sleepsService,
                            SleepSummaryJpaRepository sleepSummaryJpaRepository, EntityToXlsRowDtoConverter<SleepSummary> entityToXlsRowDtoConverter) {
        this.sleepsService = sleepsService;
        this.sleepSummaryJpaRepository = sleepSummaryJpaRepository;
        this.stringToSleepPushNotificationMapper = new StringToSleepPushNotificationMapper();
        this.entityToXlsRowDtoConverter = entityToXlsRowDtoConverter;
    }

    @GetMapping(value = "/export")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public @ResponseBody
    ResponseEntity<byte[]> export(@RequestParam("from") @NotNull Long from, @RequestParam("to") @NotNull Long to,
                                  @RequestParam("researchNumbers") @NotNull Set<String> researchNumbers) {
        try {
            if (researchNumbers.isEmpty()) {
                throw new RuntimeException("Nebyli vybrani vyzkumnici");
            }
            SleepSummaryFilterDto filterDto = new SleepSummaryFilterDto(from, to, researchNumbers);
            List<XlsRowDto> xlsDtos = entityToXlsRowDtoConverter.convertEntitiesToXlsDto(
                    sleepsService.read(filterDto));
            if (xlsDtos.size() == 0) {
                throw new EntryNotFoundException("Nebyl nalezen žádný záznam.");
            }
            File f = xlsFileExporter.exportToXlsFile(xlsDtos);
            try (
                    InputStream inputStream = new FileInputStream(f)
            ) {
                String filename = "sleeps_export_" +
                        LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                        ".xls";
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=" + filename)
                        .body(IOUtils.toByteArray(inputStream));

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * CREATE - create new SleepSummary entry endpoint
     * Garmin server sent a push (HTTP POST) notificatino with SleepSummary payload.
     */
    @PostMapping
    public ResponseEntity<Serializable> postData(@RequestBody String pBody) {
        log.info("Received push notification for SLEEPS");
        try {
            SleepsPushNotificationDto sleepsPushNotification = stringToSleepPushNotificationMapper.mapStringToDto(pBody);
            sleepsService.processSleepsPushNotificationDto(sleepsPushNotification);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred during processing of SLEEP message", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/device/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<SleepSummaryInfo> findByDeviceId(@PathVariable(value = "id") int deviceId,
                                                 @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                 @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return sleepSummaryJpaRepository.findByDevice_Id(deviceId, p, SleepSummaryInfo.class);
    }
}
