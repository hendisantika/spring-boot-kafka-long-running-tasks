package id.my.hendisantika.kafkalongrunningtasks.web.controller;

import id.my.hendisantika.kafkalongrunningtasks.jms.service.KafkaConsumerService;
import id.my.hendisantika.kafkalongrunningtasks.web.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-long-running-tasks
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/19/24
 * Time: 09:02
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TaskController {

    private final TaskService taskService;

    private final KafkaConsumerService kafkaConsumerService;

}
