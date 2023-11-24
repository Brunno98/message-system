package br.com.brunno.messagerequesthandler.integration;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

import br.com.brunno.messagerequesthandler.domain.MessageSender;
import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.brunno.messagerequesthandler.controller.dto.MessageRequestPayload;
import br.com.brunno.messagerequesthandler.controller.dto.MessageStatus;
import br.com.brunno.messagerequesthandler.controller.dto.ReceiptDto;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment=RANDOM_PORT)
public class HandleMessageRequestIntegrationTest {
    
    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    MessageSender messageSender;

    @Test
    void sendMessageShouldEnqueueMessage() {
        doReturn(true).when(messageSender).sendMessage(any());

        // message request payload
        MessageRequestPayload messageRequest = new MessageRequestPayload();
        messageRequest.setFrom("foo");
        messageRequest.setTo("bar");
        messageRequest.setMessage("HELLO");

        // do request for send message
        ResponseEntity<ReceiptDto> response = restTemplate.postForEntity("/message", messageRequest, ReceiptDto.class);
        assertThat(response.getStatusCode(), is(HttpStatus.ACCEPTED));
        assertThat(response.getBody(), is(not(nullValue())));
        String requestId = response.getBody().getRequestId();

        // get message status
        ResponseEntity<MessageStatus> responseMessageStatus = 
            restTemplate.getForEntity("/message/{requestId}", MessageStatus.class, Map.of("requestId", requestId));
        assertThat(responseMessageStatus.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseMessageStatus.getBody(), is(not(nullValue())));

        // message status should be 'enqueued'
        assertThat(responseMessageStatus.getBody().getStatus(), is("enqueued"));
    }
}
