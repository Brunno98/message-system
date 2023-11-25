package br.com.brunno.messagesender.infrastructure.jpa;

import br.com.brunno.messagesender.domain.entity.Message;
import br.com.brunno.messagesender.domain.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HistoryRepositoryJpaAdapter implements HistoryRepository {

    private final JpaHistoryRepository historyRepository;

    @Override
    public void save(Message message) {
        HistoryMessage historyMessage = HistoryMessage.from(message);

        historyRepository.save(historyMessage);
    }
}
