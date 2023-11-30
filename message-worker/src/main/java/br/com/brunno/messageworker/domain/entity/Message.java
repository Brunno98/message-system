package br.com.brunno.messageworker.domain.entity;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String to;
    private String message;
}
