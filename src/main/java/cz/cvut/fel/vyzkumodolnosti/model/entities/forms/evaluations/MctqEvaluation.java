package cz.cvut.fel.vyzkumodolnosti.model.entities.forms.evaluations;

import cz.cvut.fel.vyzkumodolnosti.model.entities.AbstractEntity;
import cz.cvut.fel.vyzkumodolnosti.model.entities.forms.submitted.MctqSubmittedForm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mctq_evaluation")
public class MctqEvaluation extends AbstractEntity {

	@NotNull
	@OneToOne
	@JoinColumn(name = "mctq_submitted_form_id", referencedColumnName = "id")
	private MctqSubmittedForm submittedForm;
	@NotNull
	@Column(name = "sqw")
	private Double SQw;
	@NotNull
	@Column(name = "guw")
	private Double GUw;
	@NotNull
	@Column(name = "sdw")
	private Double SDw;
	@NotNull
	@Column(name = "tbtw")
	private Double TBTw;
	@NotNull
	@Column(name = "msw")
	private Double MSW;
	@NotNull
	@Column(name = "sdweek")
	private Double SDweek;
	@NotNull
	@Column(name = "msfsc")
	private Double MSFsc;
	@NotNull
	@Column(name = "slossweek")
	private Double SLossweek;
	@NotNull
	@Column(name = "sjlrel")
	private Double SJLrel;
	@NotNull
	@Column(name = "sjl")
	private Double SJL;
	@NotNull
	@Column(name = "leweek")
	private Double LEweek;

	public MctqSubmittedForm getSubmittedForm() {
		return submittedForm;
	}

	public void setSubmittedForm(MctqSubmittedForm submittedForm) {
		this.submittedForm = submittedForm;
	}

	public Double getSQw() {
		return SQw;
	}

	public void setSQw(Double SQw) {
		this.SQw = SQw;
	}

	public Double getGUw() {
		return GUw;
	}

	public void setGUw(Double GUw) {
		this.GUw = GUw;
	}

	public Double getSDw() {
		return SDw;
	}

	public void setSDw(Double SDw) {
		this.SDw = SDw;
	}

	public Double getTBTw() {
		return TBTw;
	}

	public void setTBTw(Double TBTw) {
		this.TBTw = TBTw;
	}

	public Double getMSW() {
		return MSW;
	}

	public void setMSW(Double MSW) {
		this.MSW = MSW;
	}

	public Double getSDweek() {
		return SDweek;
	}

	public void setSDweek(Double SDweek) {
		this.SDweek = SDweek;
	}

	public Double getMSFsc() {
		return MSFsc;
	}

	public void setMSFsc(Double MSFsc) {
		this.MSFsc = MSFsc;
	}

	public Double getSLossweek() {
		return SLossweek;
	}

	public void setSLossweek(Double SLossweek) {
		this.SLossweek = SLossweek;
	}

	public Double getSJLrel() {
		return SJLrel;
	}

	public void setSJLrel(Double SJLrel) {
		this.SJLrel = SJLrel;
	}

	public Double getSJL() {
		return SJL;
	}

	public void setSJL(Double SJL) {
		this.SJL = SJL;
	}

	public Double getLEweek() {
		return LEweek;
	}

	public void setLEweek(Double LEweek) {
		this.LEweek = LEweek;
	}
}
