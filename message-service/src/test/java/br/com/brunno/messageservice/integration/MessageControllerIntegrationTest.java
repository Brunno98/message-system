package br.com.brunno.messageservice.integration;

import br.com.brunno.messageservice.controller.dto.MessageTextPayload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@DirtiesContext
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MessageControllerIntegrationTest {
    public static final String MESSAGE_KEY = "KEY";
    public static final String MESSAGE_TEXT = "MESSAGE TEXT";

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void whenPostMessageTextThenAMessageShouldBePersisted() {
        MessageTextPayload messageTextPayload = new MessageTextPayload(MESSAGE_KEY, MESSAGE_TEXT);

        ResponseEntity<Void> postResponse = restTemplate
                .postForEntity("/message", messageTextPayload, Void.class);

        assertThat(postResponse.getStatusCode(), is(HttpStatus.CREATED));

        ResponseEntity<MessageTextPayload> getResponse = restTemplate
                .getForEntity("/message/{messageKey}", MessageTextPayload.class, Map.of("messageKey", MESSAGE_KEY));

        assertThat(getResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(getResponse.getBody(), is(notNullValue()));
        assertThat(getResponse.getBody().getMessage(), is(MESSAGE_KEY));
        assertThat(getResponse.getBody().getText(), is(MESSAGE_TEXT));
    }
}
