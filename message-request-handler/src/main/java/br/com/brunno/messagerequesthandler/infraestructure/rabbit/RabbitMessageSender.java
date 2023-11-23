package br.com.brunno.messagerequesthandler.infraestructure.rabbit;

import br.com.brunno.messagerequesthandler.domain.MessageSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RabbitMessageSender implements MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper OM;

    @Override
    public boolean sendMessage(Object message) {
        try {
            rabbitTemplate.convertAndSend(queue.getName(), OM.writeValueAsString(message));
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
