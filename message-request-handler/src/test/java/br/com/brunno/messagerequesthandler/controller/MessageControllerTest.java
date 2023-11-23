package br.com.brunno.messagerequesthandler.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.brunno.messagerequesthandler.controller.dto.MessageRequestPayload;
import br.com.brunno.messagerequesthandler.domain.MessageService;
import br.com.brunno.messagerequesthandler.domain.entity.MessageRequest;

@WebMvcTest
public class MessageControllerTest {
    
    private static final ObjectMapper OM = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService messageService;

    @Test
    void postMessageShouldReturn202withGeneratedRequestId() throws JsonProcessingException, Exception {
        MessageRequestPayload messageRequestPayload = new MessageRequestPayload();
        messageRequestPayload.setFrom("foo");
        messageRequestPayload.setTo("bar");
        messageRequestPayload.setMessage("HELLO");

        doReturn("123").when(messageService).produceMessage(any());

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
        messageRequest.setFromFoo("foo");
        messageRequest.setToFoo("bar");
        messageRequest.setMessage("HELLO");
        messageRequest.setStatus("enqueued");
        messageRequest.setRequestId("123");
        doReturn(messageRequest).when(messageService).getMessageRequestById("123");
        
        mockMvc.perform(get("/message/{requestId}",  "123"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("status").value("enqueued"));
    }
}
