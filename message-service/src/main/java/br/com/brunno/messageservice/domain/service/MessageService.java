package br.com.brunno.messageservice.domain.service;

public interface MessageService {
    String getTextFromMessage(String message);

    void createMessage(String key, String text);
}
