package br.com.brunno.messageworker.domain;

public interface MessageSender {
    boolean sendMessage(Object message);
}
