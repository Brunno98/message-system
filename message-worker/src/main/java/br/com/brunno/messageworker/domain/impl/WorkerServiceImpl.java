package br.com.brunno.messageworker.domain.impl;

import br.com.brunno.messageworker.domain.MessageRepository;
import br.com.brunno.messageworker.domain.MessageSender;
import br.com.brunno.messageworker.domain.entity.Message;
import br.com.brunno.messageworker.domain.repository.TextRepository;
import br.com.brunno.messageworker.domain.WorkerService;
import br.com.brunno.messageworker.domain.entity.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

    private final TextRepository textRepository;
    private final MessageSender messageSender;
    private final MessageRepository messageRepository;

    @Override
    public void process(MessageRequest messageRequest) {
        Message message = messageRequest.startProcessing();
        messageRepository.save(messageRequest);

        try {
            String text = textRepository.getTextFromMessage(messageRequest.getMessage());
            message.setMessage(text);

            boolean success = messageSender.sendMessage(message);
            if (success) {
                messageRequest.processed();
            } else {
                messageRequest.failed();
            }

        } catch (Exception exception) {
            log.error("Error to process message request {}", messageRequest, exception);
            messageRequest.failed();
        }

        messageRepository.save(messageRequest);
    }

}
