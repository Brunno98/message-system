package br.com.brunno.messageworker.infrastructure.rabbit;

import br.com.brunno.messageworker.domain.entity.MessageRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequestPayload {
    private String from;
    private String to;
    private String message;
    private String requestId;

    public MessageRequest toDomain() {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setRequestId(this.requestId);
        messageRequest.setTo(this.to);
        messageRequest.setFrom(this.from);
        messageRequest.setMessage(this.message);

        return messageRequest;
    }
}
