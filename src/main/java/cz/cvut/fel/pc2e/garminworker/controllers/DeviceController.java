package cz.cvut.fel.pc2e.garminworker.controllers;

import cz.cvut.fel.pc2e.garminworker.services.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceService service;

    @Autowired
    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping(value = "/connect")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void connectDevicesToUserId() {
        service.connectExistingDevicesToUserIds();
    }
}
