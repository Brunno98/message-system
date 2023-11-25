package br.com.brunno.messagerequesthandler.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class MessageRequest {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"from\"")
    private String from;

    @Column(name = "\"to\"")
    private String to;

    private String message;

    @Setter(AccessLevel.NONE)
    private String status;

    @Setter(AccessLevel.NONE)
    private String requestId;


    public void enqueued() {
        this.status = "enqueued";
    }

    public void fail() {
        this.status = "failed";
    }

    public void generateRequestId() {
        this.requestId = UUID.randomUUID().toString();
    }
}
