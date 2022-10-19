package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.SubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedFormJpaRepository extends JpaRepository<SubmittedForm, Integer> {
}
