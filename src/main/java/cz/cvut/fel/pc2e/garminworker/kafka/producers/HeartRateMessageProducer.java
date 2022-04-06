package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import cz.cvut.fel.pc2e.garminworker.kafka.messages.HeartRateMessage;
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
public class HeartRateMessageProducer {

    private final KafkaTemplate<String, HeartRateMessage> heartRateMessageKafkaTemplate;

    @Value(value = "${kafka.topic.heartrate.name}")
    private String heartRateTopic;

    public HeartRateMessageProducer(KafkaTemplate<String, HeartRateMessage> heartRateMessageKafkaTemplate) {
        this.heartRateMessageKafkaTemplate = heartRateMessageKafkaTemplate;
    }

    public void sendHeartRateMessage(HeartRateMessage message) {

        ListenableFuture<SendResult<String, HeartRateMessage>> future = heartRateMessageKafkaTemplate.send(heartRateTopic, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, HeartRateMessage> result) {
                log.debug("Sent message=[ {} ] with offset=[ {} ] to topic: {}", message, result.getRecordMetadata().offset(), heartRateTopic);
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.error("Unable to send message=[ {} ] to topic: {} due to : {}", message, heartRateTopic, ex.getMessage());
            }
        });
    }

}
