package br.com.brunno.messagerequesthandler.domain.impl;

import br.com.brunno.messagerequesthandler.domain.MessageRepository;
import br.com.brunno.messagerequesthandler.domain.MessageSender;
import br.com.brunno.messagerequesthandler.domain.MessageService;
import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        messageRequest.enqueued();
        messageRepository.save(messageRequest);
        boolean success = messageSender.sendMessage(messageRequest);
        if (!success) {
            messageRequest.fail();
            messageRepository.save(messageRequest);
            throw new RuntimeException("Fail to enqueue message");
        }
        return messageRequest.getRequestId();
    }
}
