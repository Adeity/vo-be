package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepLevelTimeRangeMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepLevelTypeEnum;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepSummaryMessage;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(topics = {"${kafka.topic.sleep.summary.name}", "${kafka.topic.sleep.level.timerange.name}"})
@TestPropertySource(locations = "classpath:test.properties")
class SleepMessageProducerTest {

    private final static Timestamp MESSAGE_TIMESTAMP = new Timestamp(System.currentTimeMillis());
    private final static String DEVICE_ID = "deviceId";
    private final static String CALENDAR_DATE = "2021-11-21";
    private final static SleepLevelTypeEnum SLEEP_LEVEL = SleepLevelTypeEnum.DEEP;
    private final static long START_TIME_IN_SECONDS = 1637453700L;
    private final static long END_TIME_IN_SECONDS = 1637454700L;
    private final static int DURATION = 500;
    private final static int UNMEASURABLE_TIME = 50;
    private final static int DEEP_TIME = 70;
    private final static int LIGHT_TIME = 80;
    private final static int REM_TIME = 90;
    private final static int AWAKE_TIME = 10;

    @Value(value = "${kafka.topic.sleep.summary.name}")
    private String sleepSummaryTopic;

    @Value(value = "${kafka.topic.sleep.level.timerange.name}")
    private String sleepLevelTimeRangeTopic;

    @Autowired
    private SleepMessageProducer sleepMessageProducer;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;


    public SleepLevelTimeRangeMessage createSleepLevelTimeRangeTestMessage() {
        SleepLevelTimeRangeMessage sleepLevelTimeRangeMessage = new SleepLevelTimeRangeMessage();
        sleepLevelTimeRangeMessage.setMessageTimestamp(MESSAGE_TIMESTAMP);
        sleepLevelTimeRangeMessage.setDeviceId(DEVICE_ID);
        sleepLevelTimeRangeMessage.setStartTimeInSeconds(START_TIME_IN_SECONDS);
        sleepLevelTimeRangeMessage.setEndTimeInSeconds(END_TIME_IN_SECONDS);
        sleepLevelTimeRangeMessage.setSleepLevel(SLEEP_LEVEL);
        sleepLevelTimeRangeMessage.setCalendarDate(CALENDAR_DATE);

        return sleepLevelTimeRangeMessage;
    }

    public SleepSummaryMessage createSleepSummaryMessage() {
        SleepSummaryMessage sleepSummaryMessage = new SleepSummaryMessage();
        sleepSummaryMessage.setMessageTimestamp(MESSAGE_TIMESTAMP);
        sleepSummaryMessage.setDeviceId(DEVICE_ID);
        sleepSummaryMessage.setStartTimeInSeconds(START_TIME_IN_SECONDS);
        sleepSummaryMessage.setEndTimeInSeconds(END_TIME_IN_SECONDS);
        sleepSummaryMessage.setCalendarDate(CALENDAR_DATE);
        sleepSummaryMessage.setDurationInSeconds(DURATION);
        sleepSummaryMessage.setUnmeasurableSleepDurationInSeconds(UNMEASURABLE_TIME);
        sleepSummaryMessage.setDeepSleepDurationInSeconds(DEEP_TIME);
        sleepSummaryMessage.setLightSleepDurationInSeconds(LIGHT_TIME);
        sleepSummaryMessage.setRemSleepInSeconds(REM_TIME);
        sleepSummaryMessage.setAwakeDurationInSeconds(AWAKE_TIME);

        return sleepSummaryMessage;
    }

    @Test
    void sleepLevelTimeRangeMessageProducerTest_sendMessageToTopic() {
        SleepLevelTimeRangeMessage sleepLevelTimeRangeMessage = createSleepLevelTimeRangeTestMessage();
        // simulation consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, SleepLevelTimeRangeMessage> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new JsonDeserializer<>(SleepLevelTimeRangeMessage.class, false));
        Consumer<String, SleepLevelTimeRangeMessage> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, sleepLevelTimeRangeTopic);
        // WHEN
        sleepMessageProducer.sendSleepLevelTimeRangeMessage(sleepLevelTimeRangeMessage);
        // THEN
        ConsumerRecord<String, SleepLevelTimeRangeMessage> consumerRecordOfSleepLevelTimeRangeMessage = KafkaTestUtils.getSingleRecord(consumerServiceTest, sleepLevelTimeRangeTopic);
        SleepLevelTimeRangeMessage valueReceived = consumerRecordOfSleepLevelTimeRangeMessage.value();

        assertThat(valueReceived).isNotNull();
        Assertions.assertEquals(MESSAGE_TIMESTAMP, valueReceived.getMessageTimestamp());
        Assertions.assertEquals(DEVICE_ID, valueReceived.getDeviceId());
        Assertions.assertEquals(START_TIME_IN_SECONDS, valueReceived.getStartTimeInSeconds());
        Assertions.assertEquals(END_TIME_IN_SECONDS, valueReceived.getEndTimeInSeconds());
        Assertions.assertEquals(SLEEP_LEVEL, valueReceived.getSleepLevel());
        Assertions.assertEquals(CALENDAR_DATE, valueReceived.getCalendarDate());

        consumerServiceTest.close();
    }

    @Test
    void sleepSummaryMessageProducerTest_sendMessageToTopic() {
        SleepSummaryMessage sleepSummaryMessage = createSleepSummaryMessage();
        // simulation consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, SleepSummaryMessage> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new JsonDeserializer<>(SleepSummaryMessage.class, false));
        Consumer<String, SleepSummaryMessage> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, sleepSummaryTopic);
        // WHEN
        sleepMessageProducer.sendSleepSummaryMessage(sleepSummaryMessage);
        // THEN
        ConsumerRecord<String, SleepSummaryMessage> consumerRecordOfSleepSummaryMessage = KafkaTestUtils.getSingleRecord(consumerServiceTest, sleepSummaryTopic);
        SleepSummaryMessage valueReceived = consumerRecordOfSleepSummaryMessage.value();

        assertThat(valueReceived).isNotNull();
        Assertions.assertEquals(MESSAGE_TIMESTAMP, valueReceived.getMessageTimestamp());
        Assertions.assertEquals(DEVICE_ID, valueReceived.getDeviceId());
        Assertions.assertEquals(START_TIME_IN_SECONDS, valueReceived.getStartTimeInSeconds());
        Assertions.assertEquals(END_TIME_IN_SECONDS, valueReceived.getEndTimeInSeconds());
        Assertions.assertEquals(CALENDAR_DATE, valueReceived.getCalendarDate());
        Assertions.assertEquals(DURATION, valueReceived.getDurationInSeconds());
        Assertions.assertEquals(UNMEASURABLE_TIME, valueReceived.getUnmeasurableSleepDurationInSeconds());
        Assertions.assertEquals(DEEP_TIME, valueReceived.getDeepSleepDurationInSeconds());
        Assertions.assertEquals(LIGHT_TIME, valueReceived.getLightSleepDurationInSeconds());
        Assertions.assertEquals(REM_TIME, valueReceived.getRemSleepInSeconds());
        Assertions.assertEquals(AWAKE_TIME, valueReceived.getAwakeDurationInSeconds());

        consumerServiceTest.close();
    }
}