package br.com.brunno.messageworker.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequestDto {
    private String fromFoo;
    private String toFoo;
    private String message;
    private String requestId;
}
