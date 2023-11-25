package br.com.brunno.messagesender.domain.repository;

import br.com.brunno.messagesender.domain.entity.Message;

public interface HistoryRepository {
    void save(Message message);
}
