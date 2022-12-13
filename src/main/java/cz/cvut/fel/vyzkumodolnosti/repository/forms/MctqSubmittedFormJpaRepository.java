package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MctqSubmittedFormJpaRepository extends JpaRepository<MctqSubmittedForm, Integer> {
}
