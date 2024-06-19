package id.my.hendisantika.kafkalongrunningtasks.jms.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-long-running-tasks
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/19/24
 * Time: 08:54
 * To change this template use File | Settings | File Templates.
 */
@Component
@Log
@Slf4j
@KafkaListener(topics = "general-task-topic", groupId = "task-group")
public class KafKaTopicListeners {

}
