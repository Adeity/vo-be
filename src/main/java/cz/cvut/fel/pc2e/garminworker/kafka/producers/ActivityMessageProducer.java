package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import cz.cvut.fel.pc2e.garminworker.kafka.messages.ActivityMessage;
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
public class ActivityMessageProducer {

    private final KafkaTemplate<String, ActivityMessage> activityMessageKafkaTemplate;

    @Value(value = "${kafka.topic.activity.name}")
    private String activityTopic;

    public ActivityMessageProducer(KafkaTemplate<String, ActivityMessage> activityMessageKafkaTemplate) {
        this.activityMessageKafkaTemplate = activityMessageKafkaTemplate;
    }

    public void sendActivityMessage(ActivityMessage message) {

        ListenableFuture<SendResult<String, ActivityMessage>> future = activityMessageKafkaTemplate.send(activityTopic, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, ActivityMessage> result) {
                log.debug("Sent message=[ {} ] with offset=[ {} ] to topic: {}", message, result.getRecordMetadata().offset(), activityTopic);
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.error("Unable to send message=[ {} ] to topic: {} due to : {}", message, activityTopic, ex.getMessage());
            }
        });
    }

}
