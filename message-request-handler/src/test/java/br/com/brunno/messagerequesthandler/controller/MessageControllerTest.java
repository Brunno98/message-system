package br.com.brunno.messagerequesthandler.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brunno.messagerequesthandler.controller.dto.MessageRequestPayload;
import br.com.brunno.messagerequesthandler.domain.service.MessageService;
import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;

@WebMvcTest
public class MessageControllerTest {
    public static final String SENDER = "foo";
    public static final String RECEIVER = "receiver";
    public static final String MESSAGE = "HELLO";
    public static final String MESSAGE_ID = "123";
    public static final String ENQUEUED_STATUS = "enqueued";
    private static final ObjectMapper OM = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService messageService;

    @Test
    void postMessageShouldReturn202withGeneratedRequestId() throws JsonProcessingException, Exception {
        doReturn(MESSAGE_ID).when(messageService).produceMessage(any());
        MessageRequestPayload messageRequestPayload = new MessageRequestPayload(SENDER, RECEIVER, MESSAGE);

        mockMvc.perform(post("/message")
            .contentType(MediaType.APPLICATION_JSON)
            .content(OM.writeValueAsString(messageRequestPayload))
            )
            .andExpect(status().isAccepted())
            .andExpect(jsonPath("requestId").isString());
    }

    @Test
    void getMessageByRequestIdShouldReturn200withMessageStatus() throws Exception {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setFrom(SENDER);
        messageRequest.setTo(RECEIVER);
        messageRequest.setMessage(MESSAGE);
        messageRequest.enqueued();
        messageRequest.generateRequestId();
        doReturn(messageRequest).when(messageService).getMessageRequestById(messageRequest.getRequestId());
        
        mockMvc.perform(get("/message/{requestId}",  messageRequest.getRequestId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("status").value(ENQUEUED_STATUS));
    }
}
