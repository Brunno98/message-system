package br.com.brunno.messageservice.domain.service.impl;

import br.com.brunno.messageservice.domain.entity.Message;
import br.com.brunno.messageservice.domain.exception.MessageNotFoundException;
import br.com.brunno.messageservice.domain.repository.MessageRepository;
import br.com.brunno.messageservice.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public String getTextFromMessage(String messageKey) {
        Message message = messageRepository.getMessageByKey(messageKey)
                .orElseThrow(MessageNotFoundException::new);
        return message.getText();
    }
}
