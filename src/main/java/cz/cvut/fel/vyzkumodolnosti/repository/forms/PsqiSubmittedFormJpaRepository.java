package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PsqiSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.PsqiSubmittedFormInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsqiSubmittedFormJpaRepository extends JpaRepository<PsqiSubmittedForm, Integer> {
    Page<PsqiSubmittedFormInfo> findAllProjectedBy(Pageable p);
}
