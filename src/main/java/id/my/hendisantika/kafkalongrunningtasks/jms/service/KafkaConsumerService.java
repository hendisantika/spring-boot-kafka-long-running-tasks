package id.my.hendisantika.kafkalongrunningtasks.jms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

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
}
