package cz.cvut.fel.vyzkumodolnosti.controllers;

import cz.cvut.fel.vyzkumodolnosti.repository.forms.*;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/form-read")
public class FormReadController {
    private final PssSubmittedFormRepository pssSubmittedFormRepository;
    private final PsqiSubmittedFormJpaRepository psqiSubmittedFormRepository;
    private final MeqSubmittedFormJpaRepository meqSubmittedFormRepository;
    private final MctqSubmittedFormJpaRepository mctqSubmittedFormRepository;
    private final LifeSatisfactionSubmittedFormJpaRepository lifesatSubmittedFormRepository;
    private final DemoSubmittedFormJpaRepository demoSubmittedFormJpaRepository;

    @Autowired
    public FormReadController(PssSubmittedFormRepository pssSubmittedFormRepository, PsqiSubmittedFormJpaRepository psqiSubmittedFormRepository, MeqSubmittedFormJpaRepository meqSubmittedFormRepository, MctqSubmittedFormJpaRepository mctqSubmittedFormRepository, LifeSatisfactionSubmittedFormJpaRepository lifesatSubmittedFormRepository, DemoSubmittedFormJpaRepository demoSubmittedFormJpaRepository) {
        this.pssSubmittedFormRepository = pssSubmittedFormRepository;
        this.psqiSubmittedFormRepository = psqiSubmittedFormRepository;
        this.meqSubmittedFormRepository = meqSubmittedFormRepository;
        this.mctqSubmittedFormRepository = mctqSubmittedFormRepository;
        this.lifesatSubmittedFormRepository = lifesatSubmittedFormRepository;
        this.demoSubmittedFormJpaRepository = demoSubmittedFormJpaRepository;
    }


    @GetMapping(value = "/psqi")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<PsqiSubmittedFormInfo> findByDeviceIdPsqi(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return psqiSubmittedFormRepository.findAllProjectedBy(p);
    }
    @GetMapping(value = "/meq")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<MeqSubmittedFormInfo> findByDeviceIdMeq(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return meqSubmittedFormRepository.findAllProjectedBy(p);
    }
    @GetMapping(value = "/mctq")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<MctqSubmittedFormInfo> findByDeviceIdMctq(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return mctqSubmittedFormRepository.findAllProjectedBy(p);
    }
    @GetMapping(value = "/demo")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<DemoSubmittedFormInfo> findByDeviceIdDemo(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return demoSubmittedFormJpaRepository.findAllInfoProjectedBy(p);
    }
    @GetMapping(value = "/dzs")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<LifeSatisfactionSubmittedFormInfo> findByDeviceIdLifesat(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return lifesatSubmittedFormRepository.findAllInfoProjectedBy(p);
    }
    @GetMapping(value = "/pss")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')")
    public Page<PssSubmittedFormInfo> findByDeviceId(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        return pssSubmittedFormRepository.findAllInfoProjectedBy(p);
    }
}
