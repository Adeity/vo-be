package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import cz.cvut.fel.pc2e.garminworker.kafka.messages.HeartRateMessage;
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
@EmbeddedKafka(topics = {"${kafka.topic.heartrate.name}"})
@TestPropertySource(locations = "classpath:test.properties")
class HeartrateMessageProducerTest {

    private final static Timestamp MESSAGE_TIMESTAMP = new Timestamp(System.currentTimeMillis());
    private final static String DEVICE_ID = "deviceId";
    private final static int HEARTRATE = 169;
    private final static long DATETIME_IN_SECONDS = 1637453700L;

    @Value(value = "${kafka.topic.heartrate.name}")
    private String heartrateTopic;

    @Autowired
    private HeartRateMessageProducer heartRateMessageProducer;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;


    public HeartRateMessage createTestMessage() {
        HeartRateMessage heartRateMessage = new HeartRateMessage();
        heartRateMessage.setHeartRate(HEARTRATE);
        heartRateMessage.setMessageTimestamp(MESSAGE_TIMESTAMP);
        heartRateMessage.setDeviceId(DEVICE_ID);
        heartRateMessage.setDatetimeInSeconds(DATETIME_IN_SECONDS);

        return heartRateMessage;
    }

    @Test
    void activityMessageProducerTest_sendMessageToTopic() {
        HeartRateMessage heartRateMessage = createTestMessage();
        // simulation consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, HeartRateMessage> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new JsonDeserializer<>(HeartRateMessage.class, false));
        Consumer<String, HeartRateMessage> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, heartrateTopic);
        // WHEN
        heartRateMessageProducer.sendHeartRateMessage(heartRateMessage);
        // THEN
        ConsumerRecord<String, HeartRateMessage> consumerRecordOfHeartrateMessage = KafkaTestUtils.getSingleRecord(consumerServiceTest, heartrateTopic);
        HeartRateMessage valueReceived = consumerRecordOfHeartrateMessage.value();

        assertThat(valueReceived).isNotNull();
        Assertions.assertEquals(HEARTRATE, valueReceived.getHeartRate());
        Assertions.assertEquals(MESSAGE_TIMESTAMP, valueReceived.getMessageTimestamp());
        Assertions.assertEquals(DEVICE_ID, valueReceived.getDeviceId());
        Assertions.assertEquals(DATETIME_IN_SECONDS, valueReceived.getDatetimeInSeconds());

        consumerServiceTest.close();
    }
}