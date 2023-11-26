package br.com.brunno.messageworker.domain.impl;

import br.com.brunno.messageworker.domain.MessageRepository;
import br.com.brunno.messageworker.domain.MessageSender;
import br.com.brunno.messageworker.domain.repository.TextRepository;
import br.com.brunno.messageworker.domain.WorkerService;
import br.com.brunno.messageworker.domain.entity.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

    private final TextRepository textRepository;
    private final MessageSender messageSender;
    private final MessageRepository messageRepository;

    @Override
    public void process(MessageRequest messageRequest) {
        // altera status pra processing
        messageRequest.processing();

        // busca texto referente a mensagem
        String text = textRepository.getTextFromMessage(messageRequest.getMessage());
        messageRequest.setMessage(text);

        // envia pra fila
        boolean success = messageSender.sendMessage(messageRequest);
        if (success) {
            messageRequest.precessed();
        } else {
            messageRequest.failed();
        }
        messageRepository.save(messageRequest);
    }
}
