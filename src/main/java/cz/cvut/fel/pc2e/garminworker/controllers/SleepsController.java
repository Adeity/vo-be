package cz.cvut.fel.pc2e.garminworker.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.pc2e.garminworker.dto.SleepSummaryDto;
import cz.cvut.fel.pc2e.garminworker.dto.SleepsPushNotificationDto;
import cz.cvut.fel.pc2e.garminworker.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.SleepsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Slf4j
@RestController
@RequestMapping(value = "/garmin/sleeps")
public class SleepsController {
    private final SleepsService sleepsService;

    @Autowired
    public SleepsController(SleepsService sleepsService) {
        this.sleepsService = sleepsService;
    }

    @GetMapping(value = "/{id}")
    public SleepSummary getSleepSummaryById(@PathVariable Integer id) {
        return sleepsService.getSleepSummaryById(id);
    }

    /**
     * {
     * "sleeps"
     * }
     */
    @PostMapping
    public ResponseEntity<Serializable> postData(@RequestBody String pBody) {
        log.info("Received push notification for SLEEPS");
        log.info(pBody);

        //rawMessageProducer.sendRawMessage(pBody);

        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            SleepsPushNotificationDto sleepsPushNotification = om.readValue(pBody, SleepsPushNotificationDto.class);
            System.out.println(sleepsPushNotification);

//
            if (sleepsPushNotification != null && sleepsPushNotification.getSleeps() != null) {
                for (SleepSummaryDto sleepSummary : sleepsPushNotification.getSleeps()) {
                    sleepsService.processSleepSummaryDTO(sleepSummary);
                }
            }
        } catch (Exception e) {
            log.error("Exception occurred during processing of SLEEP message", e);
            //rawMessageProducer.sendRawDltMessage(pBody);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
