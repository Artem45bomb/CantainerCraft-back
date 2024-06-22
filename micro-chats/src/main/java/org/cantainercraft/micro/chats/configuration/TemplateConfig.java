package org.cantainercraft.micro.chats.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TemplateConfig {

    private String SERVICE_URL_BASE = "http://localhost:8081/micro-users/api";

    @Primary
    @Bean("webclient")
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl(SERVICE_URL_BASE)
                .build();
    }

}
