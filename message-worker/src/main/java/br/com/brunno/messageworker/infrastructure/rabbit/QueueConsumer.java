package br.com.brunno.messageworker.infrastructure.rabbit;

import br.com.brunno.messageworker.domain.WorkerService;
import br.com.brunno.messageworker.domain.dto.MessageRequestDto;
import br.com.brunno.messageworker.domain.entity.MessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@RabbitListener(queues = "${rabbit.consumer.queue.name}")
public class QueueConsumer {

    private final WorkerService service;
    private final ObjectMapper OM;

    @RabbitHandler
    public void handleMessage(String in) throws JsonProcessingException {
        // TODO: Configurar DLQ
        MessageRequestPayload payload = OM.readValue(in, MessageRequestPayload.class);

        MessageRequest messageRequest = payload.toDomain();

        service.process(messageRequest);
    }
}