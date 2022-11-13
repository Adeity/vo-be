package cz.cvut.fel.vyzkumodolnosti.repository.sleep;

import cz.cvut.fel.vyzkumodolnosti.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.vyzkumodolnosti.services.TimeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

@Repository
public class SleepSummaryRepository {
	@PersistenceContext EntityManager em;
	private final TimeComponent timeComponent;
	private final SleepSummaryJpaRepository jpaRepository;

	@Autowired
	public SleepSummaryRepository(TimeComponent timeComponent, SleepSummaryJpaRepository jpaRepository) {
		this.timeComponent = timeComponent;
		this.jpaRepository = jpaRepository;
	}

	public Optional<SleepSummary> findBySummaryId(String deviceId) {
		return jpaRepository.findBySummaryId(deviceId);
	}


	public void save(SleepSummary sleepSummary) {
		this.jpaRepository.save(sleepSummary);
	}

	public List<SleepSummary> find(SleepSummaryFilterDto filter) {
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(SleepSummary.class);
		var root = cq.from(SleepSummary.class);

		cq.select(root);
		Predicate researchNamePredicate = cb.isTrue(cb.literal(true));
		Predicate timePredicate = cb.isTrue(cb.literal(true));

		if (filter.getResearchIds() != null && !filter.getResearchIds().isEmpty()) {
			CriteriaBuilder.In<String> inClause = cb.in(root.get("device").get("researchNumber"));
			for (var s : filter.getResearchIds()) {
				inClause.value(s);
			}
			researchNamePredicate = inClause;
		}

		if (filter.getDateTo() != null && filter.getDateFrom() != null) {
			timePredicate =
				 cb.between(
						  root.get("startTimeInSeconds"),
						  timeComponent.convertLocalDateToEpochSeconds(filter.getDateFrom()),
						  timeComponent.convertLocalDateToEpochSeconds(filter.getDateTo())
				 );
		}

		cq.where(cb.and(
				researchNamePredicate,
				timePredicate
		));

		cq.orderBy(cb.desc(root.get("userAccessToken")), cb.desc(root.get("startTimeInSeconds")));

		var typedQuery = em.createQuery(cq);
		return typedQuery.getResultList();
	}

}
