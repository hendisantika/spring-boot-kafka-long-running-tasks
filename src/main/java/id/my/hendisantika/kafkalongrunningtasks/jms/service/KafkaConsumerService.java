package id.my.hendisantika.kafkalongrunningtasks.jms.service;

import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-long-running-tasks
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/19/24
 * Time: 08:56
 * To change this template use File | Settings | File Templates.
 */
@Service
@Log
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final KafkaConsumer<String, TaskStatus> kafkaConsumer;

    public TaskStatus getLatestTaskStatus(String taskId) {
        ConsumerRecord<String, TaskStatus> latestUpdate = null;
        ConsumerRecords<String, TaskStatus> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(1000));
        if (!consumerRecords.isEmpty()) {
            Iterator itr = consumerRecords.records(taskId).iterator();
            while (itr.hasNext()) {
                latestUpdate = (ConsumerRecord<String, TaskStatus>) itr.next();
            }
            log.info("Latest updated status : " + latestUpdate.value());
        }
        return latestUpdate != null ? latestUpdate.value() : null;
    }
}
