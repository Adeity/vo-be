package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import cz.cvut.fel.pc2e.garminworker.dto.ActivityTypeEnum;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.ActivityMessage;
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
@EmbeddedKafka(topics = {"${kafka.topic.activity.name}"})
@TestPropertySource(locations = "classpath:test.properties")
class ActivityMessageProducerTest {

    private final static ActivityTypeEnum ACTIVITY_TYPE = ActivityTypeEnum.AUTO_RACING;
    private final static double MET = 5.23;
    private final static Timestamp MESSAGE_TIMESTAMP = new Timestamp(System.currentTimeMillis());
    private final static String DEVICE_ID = "deviceId";
    private final static int INTENSITY = 52;
    private final static int STEPS = 169;
    private final static long DATETIME_IN_SECONDS = 1637453700L;

    @Value(value = "${kafka.topic.activity.name}")
    private String activityTopic;

    @Autowired
    private ActivityMessageProducer activityMessageProducer;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;


    public ActivityMessage createTestMessage() {
        ActivityMessage activityMessage = new ActivityMessage();
        activityMessage.setActivityType(ACTIVITY_TYPE);
        activityMessage.setMet(MET);
        activityMessage.setMessageTimestamp(MESSAGE_TIMESTAMP);
        activityMessage.setDeviceId(DEVICE_ID);
        activityMessage.setIntensity(INTENSITY);
        activityMessage.setSteps(STEPS);
        activityMessage.setDatetimeInSeconds(DATETIME_IN_SECONDS);

        return activityMessage;
    }

    @Test
    void activityMessageProducerTest_sendMessageToTopic() {
        ActivityMessage activityMessage = createTestMessage();
        // simulation consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, ActivityMessage> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new JsonDeserializer<>(ActivityMessage.class, false));
        Consumer<String, ActivityMessage> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, activityTopic);
        // WHEN
        activityMessageProducer.sendActivityMessage(activityMessage);
        // THEN
        ConsumerRecord<String, ActivityMessage> consumerRecordOfActivityMessage = KafkaTestUtils.getSingleRecord(consumerServiceTest, activityTopic);
        ActivityMessage valueReceived = consumerRecordOfActivityMessage.value();

        assertThat(valueReceived).isNotNull();
        Assertions.assertEquals(ACTIVITY_TYPE, valueReceived.getActivityType());
        Assertions.assertEquals(MET, valueReceived.getMet());
        Assertions.assertEquals(MESSAGE_TIMESTAMP, valueReceived.getMessageTimestamp());
        Assertions.assertEquals(DEVICE_ID, valueReceived.getDeviceId());
        Assertions.assertEquals(INTENSITY, valueReceived.getIntensity());
        Assertions.assertEquals(STEPS, valueReceived.getSteps());
        Assertions.assertEquals(DATETIME_IN_SECONDS, valueReceived.getDatetimeInSeconds());

        consumerServiceTest.close();
    }
}