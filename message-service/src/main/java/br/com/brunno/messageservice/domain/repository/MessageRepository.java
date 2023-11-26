package br.com.brunno.messageservice.domain.repository;

import br.com.brunno.messageservice.domain.entity.Message;

import java.util.Optional;

public interface MessageRepository {
    Optional<Message> getMessageByKey(String messageKey);
}
