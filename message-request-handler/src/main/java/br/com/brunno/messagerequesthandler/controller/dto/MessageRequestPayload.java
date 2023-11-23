package br.com.brunno.messagerequesthandler.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequestPayload {
    private String from;
    private String to;
    private String message;
}
