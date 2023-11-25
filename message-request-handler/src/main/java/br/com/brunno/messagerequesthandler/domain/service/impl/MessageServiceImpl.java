package br.com.brunno.messagerequesthandler.domain.service.impl;

import br.com.brunno.messagerequesthandler.domain.repository.MessageRepository;
import br.com.brunno.messagerequesthandler.domain.service.MessageSender;
import br.com.brunno.messagerequesthandler.domain.service.MessageService;
import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageSender messageSender;

    @Override
    public MessageRequest getMessageRequestById(String requestId) {
        return messageRepository.findByRequestId(requestId).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    @Override
    public String produceMessage(MessageRequest messageRequest) {
        messageRequest.generateRequestId();

        boolean success = messageSender.sendMessage(messageRequest);
        if (!success) {
            throw new RuntimeException("Fail to enqueue message");
        }

        messageRequest.enqueued();
        messageRepository.save(messageRequest);
        return messageRequest.getRequestId();
    }
}
