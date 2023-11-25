package br.com.brunno.messagesender.infrastructure.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Value("${rabbit.queue.message-queue.name}")
    private String messageQueueName;

    @Bean
    public Queue messageQueue() {
        return new Queue(messageQueueName);
    }
}
