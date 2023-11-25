package br.com.brunno.messagesender.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message {
    private String from;
    private String to;
    private String text;
}
