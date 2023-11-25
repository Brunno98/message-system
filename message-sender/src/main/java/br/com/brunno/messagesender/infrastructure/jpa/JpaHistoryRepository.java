package br.com.brunno.messagesender.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHistoryRepository extends JpaRepository<HistoryMessage, Long> {
}
