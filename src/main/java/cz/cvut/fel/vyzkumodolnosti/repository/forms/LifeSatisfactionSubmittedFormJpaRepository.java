package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeSatisfactionSubmittedFormJpaRepository extends JpaRepository<LifeSatisfactionSubmittedForm, Integer> {
}
