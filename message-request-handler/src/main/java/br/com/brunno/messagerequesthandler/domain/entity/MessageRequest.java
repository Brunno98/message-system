package br.com.brunno.messagerequesthandler.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class MessageRequest {
    private String fromFoo;
    private String toFoo;
    private String message;
    @Setter(AccessLevel.NONE)
    private String status;
    @Id @GeneratedValue(strategy=GenerationType.UUID)
    private String requestId;

    public void statusEnqueued() {
        this.status = "enqueued";
    }
}
