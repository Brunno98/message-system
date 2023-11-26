package br.com.brunno.messageworker.infrastructure.client;

import br.com.brunno.messageworker.domain.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TextRepositoryFeignAdapter implements TextRepository {

    private final TextFeign textFeign;

    @Override
    public String getTextFromMessage(String message) {
        return textFeign.getTextFromMessage(message);
    }
}
