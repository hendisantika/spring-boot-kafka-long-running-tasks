package id.my.hendisantika.kafkalongrunningtasks.web.service;

import id.my.hendisantika.kafkalongrunningtasks.jms.service.KafkaProducerService;
import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskRequest;
import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-long-running-tasks
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/19/24
 * Time: 09:00
 * To change this template use File | Settings | File Templates.
 */
@Log
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TaskService {

    private final KafkaConsumer<String, TaskStatus> kafkaConsumer;

    private final KafkaProducerService kafkaProducerService;

    private final KafkaAdmin kafkaAdmin;

    @Async
    public void process(String taskId, TaskRequest taskRequest, UriComponentsBuilder b) {
        try {
            createNewTopic(taskId);

            updateTaskExecutionProgess(new TaskStatus(taskId, taskRequest.getName(), 0.0f, Status.SUBMITTED));

            Thread.sleep(2000L);
            updateTaskExecutionProgess(new TaskStatus(taskId, taskRequest.getName(), 10.0f, Status.STARTED));

            Thread.sleep(5000L);
            updateTaskExecutionProgess(new TaskStatus(taskId, taskRequest.getName(), 50.0f, Status.RUNNING));

            Thread.sleep(5000L);
            updateTaskExecutionProgess(new TaskStatus(taskId, taskRequest.getName(), 100.0f, Status.FINISHED));

        } catch (InterruptedException | ExecutionException e) {
            updateTaskExecutionProgess(new TaskStatus(taskId, taskRequest.getName(), 100.0f, Status.TERMINATED));
            throw new RuntimeException(e);
        }
    }

    private void createNewTopic(String topicName) throws ExecutionException, InterruptedException {
        Map<String, String> topicConfig = new HashMap<>();
        topicConfig.put(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(24 * 60 * 60 * 1000)); // 24 hours retention
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1).configs(topicConfig);
        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
        }
        kafkaConsumer.subscribe(Collections.singletonList(topicName));
    }

}
