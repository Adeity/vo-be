package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MeqSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.MeqSubmittedFormInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeqSubmittedFormJpaRepository extends JpaRepository<MeqSubmittedForm, Integer> {
    Page<MeqSubmittedFormInfo> findAllProjectedBy(Pageable p);

}
