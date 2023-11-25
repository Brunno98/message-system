package br.com.brunno.messagesender.infrastructure.rabbit;

import br.com.brunno.messagesender.domain.entity.Message;
import br.com.brunno.messagesender.domain.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${rabbit.queue.message-queue.name}")
public class MessageQueueConsumer {

    private final ObjectMapper OM;
    private final MessageService messageService;

    @Autowired
    public MessageQueueConsumer(ObjectMapper om, MessageService messageService) {
        this.OM = om;
        this.messageService = messageService;
    }

    @RabbitHandler
    public void handleMessage(String in) throws JsonProcessingException {
        System.out.println("Processando mensage: " + in);
        MessageQueuePayload payload = OM.readValue(in, MessageQueuePayload.class);

        Message message = payload.toMessage();

        messageService.sendMessage(message);
    }
}
