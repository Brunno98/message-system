package br.com.brunno.messageservice.domain.service.impl;

import br.com.brunno.messageservice.domain.entity.Message;
import br.com.brunno.messageservice.domain.exception.InvalidMessageKeyException;
import br.com.brunno.messageservice.domain.exception.MessageNotFoundException;
import br.com.brunno.messageservice.domain.repository.MessageRepository;
import br.com.brunno.messageservice.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

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

    @Override
    public void createMessage(String key, String text) {
        Message message = new Message();
        message.setMessageKey(key);
        message.setText(text);

        messageRepository.save(message);
    }
}
