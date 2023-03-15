package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.LifeSatisfactionSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.LifeSatisfactionSubmittedFormInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeSatisfactionSubmittedFormJpaRepository extends JpaRepository<LifeSatisfactionSubmittedForm, Integer> {
    Page<LifeSatisfactionSubmittedFormInfo> findAllInfoProjectedBy(Pageable p);
}
