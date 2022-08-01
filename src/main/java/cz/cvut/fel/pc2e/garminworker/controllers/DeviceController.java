package cz.cvut.fel.pc2e.garminworker.controllers;

import cz.cvut.fel.pc2e.garminworker.services.DeviceServiceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceServiceFacade service;

    @Autowired
    public DeviceController(DeviceServiceFacade service) {
        this.service = service;
    }

    @GetMapping(value = "/connect")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void connectDevicesToUserId() {
        service.connectExistingDevicesToUserIds();
    }

	@GetMapping(value = "/research-numbers")
	public Set<String> findAllResearchNumbers() {
		return service.findAllResearchNumbers();
	}
}
