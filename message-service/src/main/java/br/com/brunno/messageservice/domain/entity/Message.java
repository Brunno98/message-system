package br.com.brunno.messageservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Entity
@NoArgsConstructor
@Data
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String messageKey;

    private String text;

    public String getMessageKey() {
        return messageKey.toUpperCase(Locale.ROOT);
    }

    public void setMessageKey(String messageKey) {
        if (null == messageKey) throw new RuntimeException("messageKey cannot be null!");
        this.messageKey = messageKey.toUpperCase(Locale.ROOT);
    }
}
