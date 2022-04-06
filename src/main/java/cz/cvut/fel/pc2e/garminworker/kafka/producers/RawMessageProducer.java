package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class RawMessageProducer {

    private final KafkaTemplate<String, String> stringKafkaTemplate;

    @Value(value = "${kafka.topic.garmin.raw}")
    private String garminRawTopic;

    @Value(value = "${kafka.topic.garmin.raw.dlt}")
    private String garminRawDltTopic;

    public RawMessageProducer(KafkaTemplate<String, String> stringKafkaTemplate) {
        this.stringKafkaTemplate = stringKafkaTemplate;
    }

    public void sendRawMessage(String message) {
        sendMessage(message, garminRawTopic);
    }

    public void sendRawDltMessage(String message) {
        sendMessage(message, garminRawDltTopic);
    }

    private void sendMessage(String message, String topic) {
        ListenableFuture<SendResult<String, String>> future = stringKafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.debug("Sent message=[ {} ] with offset=[ {} ] to topic: {}", message, result.getRecordMetadata().offset(), topic);
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.error("Unable to send message=[ {} ] to topic: {} due to : {}", message, topic, ex.getMessage());
            }
        });
    }
}
