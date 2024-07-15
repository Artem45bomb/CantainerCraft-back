package org.cantainercraft.messenger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TemplateConfig {
    private final static String CHATS_BASE_URL = "http://localhost:8081/micro-chats/api";
    private final static String USERS_BASE_URL = "http://localhost:8081/micro-users/api";

    @Primary
    @Bean("chatsClient")
    public WebClient chatClient(){
        return WebClient
                .create(CHATS_BASE_URL);
    }

    @Bean("usersClient")
    public WebClient userClient(){
        return WebClient
                .create(USERS_BASE_URL);
    }
}
