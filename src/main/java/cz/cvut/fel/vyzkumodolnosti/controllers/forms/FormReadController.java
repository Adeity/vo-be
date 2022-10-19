package cz.cvut.fel.vyzkumodolnosti.controllers.forms;

import cz.cvut.fel.vyzkumodolnosti.services.forms.submitted.PsqiSubmittedFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/form-submit")
public class FormReadController {
	private final PsqiSubmittedFormService psqiService;

	@Autowired
	public FormReadController(PsqiSubmittedFormService psqiService) {
		this.psqiService = psqiService;
	}
}
