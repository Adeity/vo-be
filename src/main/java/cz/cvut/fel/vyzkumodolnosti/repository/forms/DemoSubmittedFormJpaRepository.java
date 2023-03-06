package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.DemoSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.DemoSubmittedFormInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoSubmittedFormJpaRepository extends JpaRepository<DemoSubmittedForm, Integer> {
    Page<DemoSubmittedFormInfo> findAllInfoProjectedBy(Pageable p);
}
