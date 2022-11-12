package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsqiSubmittedFormJpaRepository extends JpaRepository<PsqiSubmittedForm, Integer> {
}
