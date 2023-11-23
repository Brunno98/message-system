package br.com.brunno.messagerequesthandler.domain;

import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageRequest, String> {
    Optional<MessageRequest> findByRequestId(String requestId);
}
