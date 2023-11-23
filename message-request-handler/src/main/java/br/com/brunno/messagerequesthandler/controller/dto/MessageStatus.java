package br.com.brunno.messagerequesthandler.controller.dto;

import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageStatus {
    private String from;
    private String to;
    private String message;
    private String status;
    
    public static MessageStatus from(MessageRequest messageRequest) {
        MessageStatus messageStatus = new MessageStatus();
        messageStatus.setFrom(messageRequest.getFromFoo());
        messageStatus.setTo(messageRequest.getToFoo());
        messageStatus.setMessage(messageRequest.getMessage());
        messageStatus.setStatus(messageRequest.getStatus());
        return messageStatus;
    }
}
