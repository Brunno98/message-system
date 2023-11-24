package br.com.brunno.messageworker.domain.impl;

import br.com.brunno.messageworker.domain.TextService;
import org.springframework.stereotype.Service;

@Service
public class DummyTextService implements TextService {
    @Override
    public String getTextFromMessage(String message) {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua. Congue eu consequat ";
    }
}
