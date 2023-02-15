package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.model.dto.UpdateResearchNumberDto;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.DeviceEntityInfo;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.DeviceRepository;
import cz.cvut.fel.vyzkumodolnosti.services.DeviceServiceFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/device")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
public class DeviceController {
    private final DeviceRepository deviceRepository;
    private final Sort s = Sort.by("allowed").descending().and(Sort.by("deregistrationTime")).and(Sort.by("researchNumber"));

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping(value = "/all")
    public List<DeviceEntityInfo> findAllResearchNumbers() {
        return deviceRepository.findAllBy(s);
    }

    @GetMapping(value = "/all-active")
    public List<DeviceEntityInfo> findAllActiveResearchNumbers() {
        return deviceRepository.findByAllowedTrueOrAllowedFalseAndDeregistrationTimeNotNull(s);
    }

    @GetMapping(value = "/info")
    public Slice<DeviceEntityInfo> findAllDeviceInfo(@RequestParam(defaultValue = "10", required = false) int pageSize, @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize, s);
        return deviceRepository.findAllBy(p, DeviceEntityInfo.class);
    }

    @PatchMapping(value = "/update-research-number")
    public int updateResearchNumber(@RequestBody @Valid UpdateResearchNumberDto dto) {
        return deviceRepository.updateResearchNumberById(dto.getNewResearchNumber(), dto.getId());
    }

    @GetMapping(value = "/invalid-number-candidates")
    public List<DeviceEntityInfo> findInvalidNameCandidates() {
        return deviceRepository.findByResearchNumberNotLike("___\\____").stream().filter(e -> (e.getAllowed() || (!e.getAllowed() && e.getDeregistrationTime() != null))).collect(Collectors.toList());
    }

}
