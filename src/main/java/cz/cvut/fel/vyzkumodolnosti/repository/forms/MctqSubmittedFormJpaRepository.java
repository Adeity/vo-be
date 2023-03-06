package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.MctqSubmittedFormInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MctqSubmittedFormJpaRepository extends JpaRepository<MctqSubmittedForm, Integer> {
    Page<MctqSubmittedFormInfo> findAllProjectedBy(Pageable p);
}
