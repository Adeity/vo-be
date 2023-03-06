package cz.cvut.fel.vyzkumodolnosti.repository.forms;

import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.PssSubmittedForm;
import cz.cvut.fel.vyzkumodolnosti.repository.forms.info.PssSubmittedFormInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PssSubmittedFormRepository extends JpaRepository<PssSubmittedForm, Integer> {
    Page<PssSubmittedFormInfo> findAllInfoProjectedBy(Pageable p);
}
