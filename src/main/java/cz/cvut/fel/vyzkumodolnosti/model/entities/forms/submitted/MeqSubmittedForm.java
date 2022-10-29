package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("meq")
public class MeqSubmittedForm extends SubmittedForm{
}
