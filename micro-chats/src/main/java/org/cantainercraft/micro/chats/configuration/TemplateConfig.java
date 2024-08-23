package org.cantainercraft.micro.chats.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TemplateConfig {
    private static final String SERVICE_URL_BASE = "http://localhost:8081/micro-users/api";
    private static final String SERVICE_URL_FILE_MANAGER= "http://localhost:8081/service-file-manager";

    @Primary
    @Bean("webclient")
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl(SERVICE_URL_BASE)
                .build();
    }

    @Bean("fileClient")
    WebClient fileClient(){
        return WebClient.builder()
                .baseUrl(SERVICE_URL_FILE_MANAGER)
                .build();
    }

}
