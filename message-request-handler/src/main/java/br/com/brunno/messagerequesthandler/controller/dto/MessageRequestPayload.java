package br.com.brunno.messagerequesthandler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestPayload {
    private String from;
    private String to;
    private String message;
}
