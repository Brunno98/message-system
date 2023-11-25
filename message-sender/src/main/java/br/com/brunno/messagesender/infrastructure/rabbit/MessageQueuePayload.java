package br.com.brunno.messagesender.infrastructure.rabbit;

import br.com.brunno.messagesender.domain.entity.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MessageQueuePayload {
    private String from;
    private String to;
    private String message;

    public Message toMessage() {
        return new Message(from, to, message);
    }
}
