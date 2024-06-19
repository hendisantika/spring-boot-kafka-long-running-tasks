package id.my.hendisantika.kafkalongrunningtasks.jms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : kafka-long-running-tasks
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/19/24
 * Time: 08:51
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;
}
