package br.com.brunno.messagesender.domain.service;

import br.com.brunno.messagesender.domain.entity.Message;
import br.com.brunno.messagesender.domain.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final HistoryRepository historyRepository;

    @Override
    public void sendMessage(Message message) {
        historyRepository.save(message);
    }
}
