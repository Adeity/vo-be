package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.services.DeviceServiceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/device")
public class DeviceController {
    private final DeviceServiceFacade service;

    @Autowired
    public DeviceController(DeviceServiceFacade service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    @GetMapping(value = "/active-research-numbers")
    public List<String> findAllActiveResearchNumbers() {
        return service.findAllActiveResearchNumbers();
    }
}
