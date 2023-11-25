package br.com.brunno.messagerequesthandler.integration;

import br.com.brunno.messagerequesthandler.controller.dto.MessageRequestPayload;
import br.com.brunno.messagerequesthandler.controller.dto.MessageStatus;
import br.com.brunno.messagerequesthandler.controller.dto.ReceiptDto;
import br.com.brunno.messagerequesthandler.domain.service.MessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment=RANDOM_PORT)
public class HandleMessageRequestIntegrationTest {
    private static final String SENDER = "foo";
    private static final String RECEIVER = "bar";
    private static final String MESSAGE = "HELLO";
    private static final String ENQUEUED_STATUS = "enqueued";
    private static final String FAILED_STATUS = "failed";
    
    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    MessageSender messageSender;

    @Test
    void sendMessageShouldEnqueueMessage() {
        doReturn(true).when(messageSender).sendMessage(any());

        // message request payload
        MessageRequestPayload messageRequest = new MessageRequestPayload(SENDER, RECEIVER, MESSAGE);

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
        assertThat(responseMessageStatus.getBody().getStatus(), is(ENQUEUED_STATUS));
    }
}
