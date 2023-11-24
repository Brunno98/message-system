package br.com.brunno.messageworker.domain;

import br.com.brunno.messageworker.domain.entity.MessageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageRequest, String> {
}
