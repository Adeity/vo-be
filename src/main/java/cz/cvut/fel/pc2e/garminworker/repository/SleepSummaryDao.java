package cz.cvut.fel.pc2e.garminworker.repository;

import cz.cvut.fel.pc2e.garminworker.model.dto.sleeps.SleepSummaryFilterDto;
import cz.cvut.fel.pc2e.garminworker.model.entities.sleeps.SleepSummary;
import cz.cvut.fel.pc2e.garminworker.services.TimeComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class SleepSummaryDao {
	@PersistenceContext EntityManager em;
	private final TimeComponent timeComponent;
	private final SleepSummaryJpaRepository sleepSummaryJpaRepository;

	@Autowired
	public SleepSummaryDao(TimeComponent timeComponent, SleepSummaryJpaRepository sleepSummaryJpaRepository) {
		this.timeComponent = timeComponent;
		this.sleepSummaryJpaRepository = sleepSummaryJpaRepository;
	}

	public Optional<SleepSummary> findBySummaryId(String deviceId) {
		return sleepSummaryJpaRepository.findBySummaryId(deviceId);
	}

	public List<SleepSummary> findAllSorted(Long timeBoundary) {
		return sleepSummaryJpaRepository.findAllSorted(timeBoundary);
	}

	public List<SleepSummary> findFilteredByDateAndResearchIds(long fromSeconds, long toSeconds, Set<String> researchIds) {
		return sleepSummaryJpaRepository.findFilteredByDateAndResearchIds(fromSeconds, toSeconds, researchIds);
	}

	public void save(SleepSummary sleepSummary) {
		this.sleepSummaryJpaRepository.save(sleepSummary);
	}

	public Optional<SleepSummary> findById(Integer id) {
		return this.sleepSummaryJpaRepository.findById(id);
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
