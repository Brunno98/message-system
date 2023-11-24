package br.com.brunno.messageworker.infrastructure.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.consumer.queue.name}")
    private String consumerQueueName;
    @Value("${rabbit.producer.queue.name}")
    private String producerQueueName;

    @Bean
    public Queue consumerQueue() {
        return new Queue(consumerQueueName);
    }

    @Bean
    public Queue producerQueue() {
        return new Queue(producerQueueName);
    }
}
