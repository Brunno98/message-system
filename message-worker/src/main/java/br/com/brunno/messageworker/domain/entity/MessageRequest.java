package br.com.brunno.messageworker.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class MessageRequest {
    private String fromFoo;
    private String toFoo;
    private String message;
    @Id
    private String requestId;
    @Setter(AccessLevel.NONE)
    private String status;

    public void processing() {
        this.status = "processing";
    }

    public void precessed() {
        this.status = "processed";
    }

    public void failed() {
        this.status = "failed";
    }
}
