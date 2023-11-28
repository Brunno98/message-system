package br.com.brunno.messageservice.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MessageTextPayload {

    private String message;
    private String text;

    public MessageTextPayload(String key, String text) {
        this.message = key;
        this.text = text;
    }
}
