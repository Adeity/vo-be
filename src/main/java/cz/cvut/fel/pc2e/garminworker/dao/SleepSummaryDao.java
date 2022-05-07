package cz.cvut.fel.pc2e.garminworker.dao;

import cz.cvut.fel.pc2e.garminworker.entities.sleeps.SleepSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Slf4j
@Repository
public class SleepSummaryDao extends BaseDao<SleepSummary> {
    protected SleepSummaryDao() {
        super(SleepSummary.class);
    }

    public SleepSummary findBySummaryId(String summaryId) {
        SleepSummary s;
        try {
            s = em.createNamedQuery("SleepSummary.findBySummaryId", SleepSummary.class).setParameter("summaryId", summaryId)
                    .getSingleResult();
            return s;
        } catch (NoResultException e) {
            return null;
        }

    }

    public SleepSummary findBySummaryUserAndDate(String userId, Long startTime) {
        try {
            return em.createNamedQuery("SleepSummary.findBySummaryUserAndDate", SleepSummary.class)
                    .setParameter("userId", userId)
                    .setParameter("startTime", startTime)
                    .getSingleResult();
        } catch (NoResultException e) {
            log.info("No existing data with userId ${} and start time ${} found", userId, startTime);
            return null;
        }

    }
}
