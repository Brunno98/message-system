package br.com.brunno.messageworker.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${feign.client.config.text.url}", name = "TextFeign")
public interface TextFeign {

    @GetMapping("/message/{message}")
    String getTextFromMessage(@PathVariable String message);
}
