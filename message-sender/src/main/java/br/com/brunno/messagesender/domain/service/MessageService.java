package br.com.brunno.messagesender.domain.service;

import br.com.brunno.messagesender.domain.entity.Message;

public interface MessageService {
    void sendMessage(Message message);
}
