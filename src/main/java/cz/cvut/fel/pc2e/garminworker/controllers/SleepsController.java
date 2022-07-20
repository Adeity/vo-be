package cz.cvut.fel.pc2e.garminworker.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.sleeps.SleepsPushNotificationService;
import cz.cvut.fel.pc2e.garminworker.services.sleeps.SleepsService;
import cz.cvut.fel.pc2e.garminworker.services.sleeps.SleepsXlsExporter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/garmin/sleeps")
public class SleepsController {
    private final SleepsService sleepsService;
    private final SleepsXlsExporter sleepsXlsExporter;
    private final SleepsPushNotificationService sleepsPushNotificationService;

    @Autowired
    public SleepsController(SleepsService sleepsService, SleepsXlsExporter SleepsXlsExporter, SleepsPushNotificationService sleepsPushNotificationService) {
        this.sleepsService = sleepsService;
        this.sleepsXlsExporter = SleepsXlsExporter;
        this.sleepsPushNotificationService = sleepsPushNotificationService;
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SleepSummary getSleepSummaryById(@PathVariable Integer id) {
        return sleepsService.getSleepSummaryById(id);
    }

    @GetMapping(
            value = "/export",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody ResponseEntity<byte[]> export() {
        File f = sleepsXlsExporter.getSleepsXls(
                this.findAll(3)
        );
        try (
                InputStream inputStream = new FileInputStream(f);
                ){
            String filename = "sleeps_export_" +
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                    ".xls";
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename="+filename
                    )
                    .body(IOUtils.toByteArray(inputStream));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping(value = "/all/{numOfDays}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<SleepSummary> findAll(@PathVariable Integer numOfDays) {
        return sleepsService.getSleepsFromLastNDays(numOfDays);
    }

    /**
     * CREATE - create new SleepSummary entry endpoint
     * Garmin server sent a push (HTTP POST) notificatino with SleepSummary payload.
     */
    @PostMapping
    public ResponseEntity<Serializable> postData(@RequestBody String pBody) {
        log.info("Received push notification for SLEEPS");
        try {
            SleepsPushNotificationDto sleepsPushNotification = sleepsPushNotificationService.mapStringToDto(pBody);
            sleepsService.processSleepsPushNotificationDto(sleepsPushNotification);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred during processing of SLEEP message", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
