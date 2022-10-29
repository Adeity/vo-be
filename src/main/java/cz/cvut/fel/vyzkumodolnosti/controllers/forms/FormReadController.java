package cz.cvut.fel.vyzkumodolnosti.controllers.forms;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.services.forms.submitted.PsqiSubmittedFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/form-read")
public class FormReadController {
	private final PsqiSubmittedFormService psqiService;

	@Autowired
	public FormReadController(PsqiSubmittedFormService psqiService) {
		this.psqiService = psqiService;
	}

	@GetMapping(value = "/psqi")
	public List<PsqiSubmittedFormDto> getPsqi() {
		return psqiService.findAll();
	}
	@GetMapping(value = "/psqitime")
	public List<PsqiSubmittedFormDto> getPsqiTime() {
		return psqiService.findAllSortedByTime();
	}
	@GetMapping(value = "/psqir")
	public List<PsqiSubmittedFormDto> getPsqiRes() {
		return psqiService.findAllSortedByRespondentAndTime();
	}

}
