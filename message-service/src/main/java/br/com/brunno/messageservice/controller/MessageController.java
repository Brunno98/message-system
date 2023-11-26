package br.com.brunno.messageservice.controller;

import br.com.brunno.messageservice.domain.exception.MessageNotFoundException;
import br.com.brunno.messageservice.domain.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{message}")
    public ResponseEntity<String> getTextFromMessage(@PathVariable String message) {
        String text = messageService.getTextFromMessage(message);
        return ResponseEntity.ok(text);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMessageNotFound() {
        String errorMessage = "message not found";
        return ResponseEntity.status(404).body(Map.of("error", errorMessage));
    }
}
