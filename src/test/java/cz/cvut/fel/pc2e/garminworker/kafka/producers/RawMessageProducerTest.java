package cz.cvut.fel.pc2e.garminworker.kafka.producers;

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
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(topics = {"${kafka.topic.garmin.raw}", "${kafka.topic.garmin.raw.dlt}"})
@TestPropertySource(locations = "classpath:test.properties")
class RawMessageProducerTest {

    private final static String MESSAGE = "testMessage";

    @Value(value = "${kafka.topic.garmin.raw}")
    private String rawTopic;

    @Value(value = "${kafka.topic.garmin.raw.dlt}")
    private String rawDltTopic;

    @Autowired
    private RawMessageProducer rawMessageProducer;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;


    @Test
    void rawMessageProducerTest_sendMessageToTopic() {
        // simulation consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new StringDeserializer());
        Consumer<String, String> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, rawTopic);
        // WHEN
        rawMessageProducer.sendRawMessage(MESSAGE);
        // THEN
        ConsumerRecord<String, String> consumerRecordOfHeartrateMessage = KafkaTestUtils.getSingleRecord(consumerServiceTest, rawTopic);
        String valueReceived = consumerRecordOfHeartrateMessage.value();

        assertThat(valueReceived).isNotNull();
        Assertions.assertEquals(MESSAGE, valueReceived);

        consumerServiceTest.close();
    }

    @Test
    void rawDltMessageProducerTest_sendMessageToTopic() {
        // simulation consumer
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new StringDeserializer());
        Consumer<String, String> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, rawDltTopic);
        // WHEN
        rawMessageProducer.sendRawDltMessage(MESSAGE);
        // THEN
        ConsumerRecord<String, String> consumerRecordOfHeartrateMessage = KafkaTestUtils.getSingleRecord(consumerServiceTest, rawDltTopic);
        String valueReceived = consumerRecordOfHeartrateMessage.value();

        assertThat(valueReceived).isNotNull();
        Assertions.assertEquals(MESSAGE, valueReceived);

        consumerServiceTest.close();
    }
}