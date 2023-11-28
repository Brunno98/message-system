package br.com.brunno.messageservice.domain.entity;

import br.com.brunno.messageservice.domain.exception.InvalidMessageKeyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {

    @Test
    void whenCreateMessageWithLowerCaseKeyThenShouldThrowsException() {
        Message message = new Message();

        assertThrows(InvalidMessageKeyException.class,
                () -> message.setMessageKey("key"));
    }

    @Test
    void whenCreateMessageWithEmptyKeyShouldThrowsException() {
        Message message = new Message();

        assertThrows(InvalidMessageKeyException.class,
                () -> message.setMessageKey(""));
    }

    @Test
    void whenCreateMessageWithNullKeyShouldThrowsException() {
        Message message = new Message();

        assertThrows(InvalidMessageKeyException.class,
                () -> message.setMessageKey(null));
    }
}
