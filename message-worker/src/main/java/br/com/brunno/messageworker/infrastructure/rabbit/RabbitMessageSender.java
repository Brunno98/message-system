package br.com.brunno.messageworker.infrastructure.rabbit;

import br.com.brunno.messageworker.domain.MessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMessageSender implements MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final Queue producerQueue;
    private final ObjectMapper OM;

    @Override
    public boolean sendMessage(Object message) {
        try {
            rabbitTemplate.convertAndSend(producerQueue.getName(), OM.writeValueAsString(message));
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
