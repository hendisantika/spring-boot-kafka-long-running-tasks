package id.my.hendisantika.kafkalongrunningtasks.web.service;

import id.my.hendisantika.kafkalongrunningtasks.jms.service.KafkaProducerService;
import id.my.hendisantika.kafkalongrunningtasks.web.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

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

}
