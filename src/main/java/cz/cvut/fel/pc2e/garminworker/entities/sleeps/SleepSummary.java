package cz.cvut.fel.pc2e.garminworker.entities.sleeps;

import cz.cvut.fel.pc2e.garminworker.entities.AbstractEntity;
import cz.cvut.fel.pc2e.garminworker.entities.ValidationTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "sleeps")
public class SleepSummary extends AbstractEntity {

    private String userId;

    private String userAccessToken;

    private String summaryId;

    private String calendarDate;

    private Integer durationInSeconds;

    private Long startTimeInSeconds;

    private Long startTimeOffsetInSeconds;

    private Integer unmeasurableSleepInSeconds;

    private Integer deepSleepDurationInSeconds;

    private Integer lightSleepDurationInSeconds;

    private Integer remSleepInSeconds;

    private Integer awakeDurationInSeconds;

    @OneToOne(cascade = CascadeType.ALL)
    private SleepLevelsMap sleepLevelsMap;

    private ValidationTypeEnum validation;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeOffsetSleepRespiration> timeOffsetSleepRespiration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TimeOffsetSleepSpo2> timeOffsetSleepSpo2;

    @OneToOne(cascade = CascadeType.ALL)
    private OverallSleepScore overallSleepScore;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SleepScore> sleepScores;

    @Column(name = "delete_flag", columnDefinition = "boolean default false")
    private boolean deleteFlag = false;
}
