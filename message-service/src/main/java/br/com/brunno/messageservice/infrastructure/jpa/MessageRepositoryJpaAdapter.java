package br.com.brunno.messageservice.infrastructure.jpa;

import br.com.brunno.messageservice.domain.entity.Message;
import br.com.brunno.messageservice.domain.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MessageRepositoryJpaAdapter implements MessageRepository {

    private final MessageRepositoryJpa repository;

    @Override
    public Optional<Message> getMessageByKey(String messageKey) {
        return repository.findByMessageKey(messageKey);
    }

    @Override
    public void save(Message message) {
        repository.save(message);
    }
}
