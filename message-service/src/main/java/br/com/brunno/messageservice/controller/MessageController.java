package br.com.brunno.messageservice.controller;

import br.com.brunno.messageservice.controller.dto.MessageTextPayload;
import br.com.brunno.messageservice.domain.entity.Message;
import br.com.brunno.messageservice.domain.exception.InvalidMessageKeyException;
import br.com.brunno.messageservice.domain.exception.MessageNotFoundException;
import br.com.brunno.messageservice.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{message}")
    public ResponseEntity<MessageTextPayload> getTextFromMessage(@PathVariable String message) {
        String text = messageService.getTextFromMessage(message);
        return ResponseEntity.ok(new MessageTextPayload(message, text));
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMessageNotFound() {
        String errorMessage = "message not found";
        return ResponseEntity.status(404).body(Map.of("error", errorMessage));
    }

    @PostMapping
    public ResponseEntity<Void> createMessageText(@RequestBody MessageTextPayload payload) {
        messageService.createMessage(payload.getMessage(), payload.getText());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(InvalidMessageKeyException.class)
    public ResponseEntity<Map<String, String>> handle(InvalidMessageKeyException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }
}
