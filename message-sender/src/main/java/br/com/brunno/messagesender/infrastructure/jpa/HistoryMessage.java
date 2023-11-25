package br.com.brunno.messagesender.infrastructure.jpa;

import br.com.brunno.messagesender.domain.entity.Message;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class HistoryMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"from\"")
    private String from;

    @Column(name = "\"to\"")
    private String to;

    private String text;

    public static HistoryMessage from(Message message) {
        HistoryMessage historyMessage = new HistoryMessage();
        historyMessage.setFrom(message.getTo());
        historyMessage.setTo(message.getTo());
        historyMessage.setText(message.getText());
        return historyMessage;
    }
}
