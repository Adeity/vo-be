package cz.cvut.fel.pc2e.garminworker.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    /*

    @Value(value = "${kafka.topic.heartrate.name}")
    private String heartRateTopic;

    @Value(value = "${kafka.topic.activity.name}")
    private String activityTopic;

    @Value(value = "${kafka.topic.garmin.raw}")
    private String garminRawTopic;

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic heartRateTopic() {
        return new NewTopic(heartRateTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic activityTopic() {
        return new NewTopic(activityTopic, 1, (short) 1);
    }

    @Bean
    public NewTopic garminRawTopic() {
        return new NewTopic(garminRawTopic, 1, (short) 1);
    }

     */
}
