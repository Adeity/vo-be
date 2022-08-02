package cz.cvut.fel.pc2e.garminworker.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.sleeps.StringToSleepPushNotificationMapper;
import cz.cvut.fel.pc2e.garminworker.services.sleeps.SleepsService;
import cz.cvut.fel.pc2e.garminworker.xls.EntityToXlsRowDtoConverter;
import cz.cvut.fel.pc2e.garminworker.xls.XlsFileExporter;
import cz.cvut.fel.pc2e.garminworker.xls.XlsRowDto;
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
import java.io.IOException;
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
    private final StringToSleepPushNotificationMapper stringToSleepPushNotificationMapper;
	private final XlsFileExporter xlsFileExporter = new XlsFileExporter();
	private final EntityToXlsRowDtoConverter<SleepSummary> entityToXlsRowDtoConverter;

    @Autowired
    public SleepsController(SleepsService sleepsService, StringToSleepPushNotificationMapper stringToSleepPushNotificationMapper,
			EntityToXlsRowDtoConverter<SleepSummary> entityToXlsRowDtoConverter) {
        this.sleepsService = sleepsService;
        this.stringToSleepPushNotificationMapper = stringToSleepPushNotificationMapper;
		this.entityToXlsRowDtoConverter = entityToXlsRowDtoConverter;
	}

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SleepSummary getSleepSummaryById(@PathVariable Integer id) {
        return sleepsService.getSleepSummaryById(id);
    }

    @PostMapping(
            value = "/export",
			consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public @ResponseBody ResponseEntity<byte[]> export(@RequestBody SleepSummaryFilterDto filterDto) {
		try {
			List<XlsRowDto> xlsDtos = entityToXlsRowDtoConverter.convertEntitiesToXlsDto(
					sleepsService.read(filterDto)
			);
			File f = xlsFileExporter.exportToXlsFile(xlsDtos);

			try (
					InputStream inputStream = new FileInputStream(f)
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
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping(value = "/all/{numOfDays}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SleepSummary> findAll(@PathVariable Integer numOfDays) {
		return sleepsService.getSleepsFromLastNDays(numOfDays);
    }

	@PostMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<SleepSummary> filter(@RequestBody SleepSummaryFilterDto filter) {
		return sleepsService.read(filter);
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
}
