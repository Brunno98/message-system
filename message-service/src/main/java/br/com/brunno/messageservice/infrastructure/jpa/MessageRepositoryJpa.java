package br.com.brunno.messageservice.infrastructure.jpa;

import br.com.brunno.messageservice.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepositoryJpa extends JpaRepository<Message, Long> {
    Optional<Message> findByMessageKey(String messageKey);
}
