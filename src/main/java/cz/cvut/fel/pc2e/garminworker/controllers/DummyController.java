package cz.cvut.fel.pc2e.garminworker.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Map;

@Slf4j
@Controller
public class DummyController {

    @PostMapping("/garmin/test")
    public ResponseEntity<Serializable> postEpochs(@RequestBody String pBody) {
        log.info("Received push notification for TEST endpoint");

        log.info(pBody);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/dummy/a")
    public String allow () {
        return "ahoj";
    }

    @GetMapping("/dummy/b")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String dontAllow () {
        return "ahoj usere";
    }

}
