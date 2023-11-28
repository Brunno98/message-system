package br.com.brunno.messageservice.controller;

import br.com.brunno.messageservice.controller.dto.MessageTextPayload;
import br.com.brunno.messageservice.domain.exception.InvalidMessageKeyException;
import br.com.brunno.messageservice.domain.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MessageControllerTest {
    public static final ObjectMapper OM = new ObjectMapper();
    public static final String MESSAGE_KEY = "KEY";
    public static final String MESSAGE_TEXT = "message text";
    public static final String MESSAGE_KEY_LOWER_CASE = MESSAGE_KEY.toLowerCase(Locale.ROOT);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService messageService;

    @Test
    void whenPostMessageThenShouldReturn201() throws Exception {
        MessageTextPayload messageTextPayload = new MessageTextPayload(MESSAGE_KEY, MESSAGE_TEXT);

        mockMvc.perform(
                post("/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(OM.writeValueAsString(messageTextPayload))
        ).andExpect(status().isCreated());
    }

    @Test
    void whenGetMessageByKeyThenShouldReturn200AndMessageData() throws Exception {
        doReturn(MESSAGE_TEXT).when(messageService).getTextFromMessage(MESSAGE_KEY);

        mockMvc.perform(get("/message/" + MESSAGE_KEY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value(MESSAGE_KEY))
                .andExpect(jsonPath("text").value(MESSAGE_TEXT));
    }

    @Test
    void whenCreateMessageWithLowerCaseKeyShouldReturnBadRequest() throws Exception {
        doThrow(new InvalidMessageKeyException("message field should be uppercase")).when(messageService).createMessage(MESSAGE_KEY_LOWER_CASE, MESSAGE_TEXT);
        MessageTextPayload messageTextPayload = new MessageTextPayload(MESSAGE_KEY_LOWER_CASE, MESSAGE_TEXT);

        mockMvc.perform(
                post("/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OM.writeValueAsString(messageTextPayload))
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("error").value("message field should be uppercase"));
    }
}

