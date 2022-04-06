package cz.cvut.fel.pc2e.garminworker.kafka.producers;

import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepLevelTimeRangeMessage;
import cz.cvut.fel.pc2e.garminworker.kafka.messages.SleepSummaryMessage;
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
public class SleepMessageProducer {

    private final KafkaTemplate<String, SleepSummaryMessage> sleepSummaryMessageKafkaTemplate;
    private final KafkaTemplate<String, SleepLevelTimeRangeMessage> sleepLevelTimeRangeMessageKafkaTemplate;

    @Value(value = "${kafka.topic.sleep.summary.name}")
    private String sleepSummaryTopic;

    @Value(value = "${kafka.topic.sleep.level.timerange.name}")
    private String sleepLevelTimeRangeTopic;

    public SleepMessageProducer(KafkaTemplate<String, SleepSummaryMessage> sleepSummaryMessageKafkaTemplate, KafkaTemplate<String, SleepLevelTimeRangeMessage> sleepLevelTimeRangeMessageKafkaTemplate) {
        this.sleepSummaryMessageKafkaTemplate = sleepSummaryMessageKafkaTemplate;
        this.sleepLevelTimeRangeMessageKafkaTemplate = sleepLevelTimeRangeMessageKafkaTemplate;
    }

    public void sendSleepSummaryMessage(SleepSummaryMessage message) {

        ListenableFuture<SendResult<String, SleepSummaryMessage>> future = sleepSummaryMessageKafkaTemplate.send(sleepSummaryTopic, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, SleepSummaryMessage> result) {
                log.debug("Sent message=[ {} ] with offset=[ {} ] to topic: {}", message, result.getRecordMetadata().offset(), sleepSummaryTopic);
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.error("Unable to send message=[ {} ] to topic: {} due to : {}", message, sleepSummaryTopic, ex.getMessage());
            }
        });
    }

    public void sendSleepLevelTimeRangeMessage(SleepLevelTimeRangeMessage message) {

        ListenableFuture<SendResult<String, SleepLevelTimeRangeMessage>> future = sleepLevelTimeRangeMessageKafkaTemplate.send(sleepLevelTimeRangeTopic, message);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, SleepLevelTimeRangeMessage> result) {
                log.debug("Sent message=[ {} ] with offset=[ {} ] to topic: {}", message, result.getRecordMetadata().offset(), sleepLevelTimeRangeTopic);
            }

            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.error("Unable to send message=[ {} ] to topic: {} due to : {}", message, sleepSummaryTopic, ex.getMessage());
            }
        });
    }
}
