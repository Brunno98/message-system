package br.com.brunno.messageservice.domain.exception;

public class InvalidMessageKeyException extends RuntimeException {

    public InvalidMessageKeyException(String message) {
        super(message);
    }
}
