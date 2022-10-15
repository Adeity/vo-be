package cz.cvut.fel.vyzkumodolnosti.controllers.forms;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.services.forms.submitted.PsqiSubmittedFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/form-submit")
public class FormSubmitController {
	private final PsqiSubmittedFormService psqiService;

	@Autowired
	public FormSubmitController(PsqiSubmittedFormService psqiService) {
		this.psqiService = psqiService;
	}

	@PostMapping(value = "/psqi")
	public void submitPsqi(PsqiSubmittedFormDto dto) {
		psqiService.processSubmittedForm(dto);
	}
}
