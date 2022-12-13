package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeqSubmittedFormJpaRepository extends JpaRepository<MeqSubmittedForm, Integer> {
}
