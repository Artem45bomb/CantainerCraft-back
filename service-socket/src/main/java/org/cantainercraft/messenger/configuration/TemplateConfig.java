package org.cantainercraft.messenger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TemplateConfig {
    private final static String CHATS_BASE_URL = "http://localhost:8081/micro-chats/";

    @Bean("chatsClient")
    public WebClient webClient(){
        return WebClient
                .create(CHATS_BASE_URL);
    }
}
