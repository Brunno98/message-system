package br.com.brunno.messageservice;

import br.com.brunno.messageservice.domain.entity.Message;
import br.com.brunno.messageservice.infrastructure.jpa.MessageRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}
}
