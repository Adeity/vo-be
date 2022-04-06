package cz.cvut.fel.pc2e.garminworker.configuration;

import cz.cvut.fel.pc2e.garminworker.kafka.messages.ActivityMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.HeartRateMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepLevelTimeRangeMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepSummaryMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {
    /*

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, String> stringKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, HeartRateMessage> heartRateProducerFactory() {
        return new DefaultKafkaProducerFactory<>(createConfigProps());
    }

    @Bean
    public KafkaTemplate<String, HeartRateMessage> heartRateMessageKafkaTemplate() {
        return new KafkaTemplate<>(heartRateProducerFactory());
    }

    @Bean
    public ProducerFactory<String, ActivityMessage> activityProducerFactory() {
        return new DefaultKafkaProducerFactory<>(createConfigProps());
    }

    @Bean
    public KafkaTemplate<String, ActivityMessage> activityMessageKafkaTemplate() {
        return new KafkaTemplate<>(activityProducerFactory());
    }

    @Bean
    public ProducerFactory<String, SleepSummaryMessage> sleepProducerFactory() {
        return new DefaultKafkaProducerFactory<>(createConfigProps());
    }

    @Bean
    public KafkaTemplate<String, SleepSummaryMessage> sleepSummaryMessageKafkaTemplate() {
        return new KafkaTemplate<>(sleepProducerFactory());
    }

    @Bean
    public ProducerFactory<String, SleepLevelTimeRangeMessage> sleepLevelTimeRangeProducerFactory() {
        return new DefaultKafkaProducerFactory<>(createConfigProps());
    }

    @Bean
    public KafkaTemplate<String, SleepLevelTimeRangeMessage> sleepLevelTimeRangeMessageKafkaTemplate() {
        return new KafkaTemplate<>(sleepLevelTimeRangeProducerFactory());
    }

    private Map<String, Object> createConfigProps() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return configProps;
    }

     */
}
