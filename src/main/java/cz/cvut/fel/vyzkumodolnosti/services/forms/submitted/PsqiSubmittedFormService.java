package cz.cvut.fel.vyzkumodolnosti.services.forms.submitted;

import cz.cvut.fel.vyzkumodolnosti.model.dto.forms.submitted.PsqiSubmittedFormDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.PsqiSubmittedFormRepository;
import cz.cvut.fel.vyzkumodolnosti.services.forms.mapper.PsqiSubmittedFormDtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PsqiSubmittedFormService {
	private final PsqiSubmittedFormRepository psqiRepository;

	@Autowired
	public PsqiSubmittedFormService(PsqiSubmittedFormRepository psqiRepository) {
		this.psqiRepository = psqiRepository;
	}

	public void processSubmittedForm(PsqiSubmittedFormDto dto) {
		PsqiSubmittedForm entity = PsqiSubmittedFormDtoEntityMapper.mapDtoToEntity(dto);
		psqiRepository.save(entity);
	}

	public PsqiSubmittedForm findById(Integer id) {
		return psqiRepository.findById(id);
	}

}
