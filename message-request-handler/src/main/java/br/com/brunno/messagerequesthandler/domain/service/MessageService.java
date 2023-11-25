package br.com.brunno.messagerequesthandler.domain.service;

import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;

public interface MessageService {

    MessageRequest getMessageRequestById(String requestId);

    String produceMessage(MessageRequest messageRequest);
}
