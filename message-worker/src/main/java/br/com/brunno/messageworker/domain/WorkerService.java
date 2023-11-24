package br.com.brunno.messageworker.domain;

import br.com.brunno.messageworker.domain.entity.MessageRequest;

public interface WorkerService {
    void process(MessageRequest dto);
}
