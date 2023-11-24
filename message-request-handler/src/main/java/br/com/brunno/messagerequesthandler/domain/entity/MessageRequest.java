package br.com.brunno.messagerequesthandler.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class MessageRequest {
    @Column(name = "\"from\"")
    private String from;
    @Column(name = "\"to\"")
    private String to;
    private String message;
    @Setter(AccessLevel.NONE)
    private String status;
    @Id @GeneratedValue(strategy=GenerationType.UUID)
    private String requestId;

    public void enqueued() {
        this.status = "enqueued";
    }

    public void fail() {
        this.status = "failed";
    }
}
