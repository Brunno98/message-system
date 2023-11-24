package br.com.brunno.messagerequesthandler.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunno.messagerequesthandler.controller.dto.MessageRequestPayload;
import br.com.brunno.messagerequesthandler.controller.dto.MessageStatus;
import br.com.brunno.messagerequesthandler.controller.dto.ReceiptDto;
import br.com.brunno.messagerequesthandler.domain.MessageService;
import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {
    
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<ReceiptDto> sendMesssage(@RequestBody MessageRequestPayload messageRequestPayload) {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setFrom(messageRequestPayload.getFrom());
        messageRequest.setTo(messageRequestPayload.getTo());
        messageRequest.setMessage(messageRequestPayload.getMessage());

        String requestId = messageService.produceMessage(messageRequest);

        ReceiptDto receipt = new ReceiptDto();
        receipt.setRequestId(requestId);

        return ResponseEntity.accepted().body(receipt);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<MessageStatus> getMessage(@PathVariable String requestId) {

        MessageRequest messageRequest = messageService.getMessageRequestById(requestId);
        MessageStatus messageStatus = MessageStatus.from(messageRequest);
        return ResponseEntity.ok().body(messageStatus);
    }
}
