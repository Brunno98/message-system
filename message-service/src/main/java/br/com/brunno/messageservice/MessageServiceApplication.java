package br.com.brunno.messageservice;

import br.com.brunno.messageservice.domain.entity.Message;
import br.com.brunno.messageservice.infrastructure.jpa.MessageRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class MessageServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

	private final MessageRepositoryJpa messageRepository;

	@Override
	public void run(String... args) throws Exception {
		if(messageRepository.findByMessageKey("HELLO").isPresent()) {
			System.out.println("Menssagem existe");
		} else {
			Message message = new Message();
			message.setMessageKey("HELLO");
			message.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
					" sed do eiusmod tempor incididunt ut labore et dolore magna" +
					" aliqua. Massa placerat duis ultricies lacus sed turpis tincidunt. " +
					"Maecenas ultricies mi eget mauris pharetra et.");
			messageRepository.save(message);
			System.out.println("Mensagem criada na inicialização");
		};
	}
}
