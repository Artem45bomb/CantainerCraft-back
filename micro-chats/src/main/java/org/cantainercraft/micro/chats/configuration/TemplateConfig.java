package org.cantainercraft.micro.chats.configuration;



import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Configuration
public class TemplateConfig {
    @Value("${GATEWAY_URL_BASE}")
    private String GATEWAY_URL_BASE;
    private String SERVICE_URL_BASE;
    private String SERVICE_URL_FILE_MANAGER;
    private final Set<String> profiles;

    public TemplateConfig(Environment environment){
        profiles = Arrays.stream(environment.getActiveProfiles())
                .collect(Collectors.toSet());
    }

    @PostConstruct
    public void init(){
        if(profiles.contains("dev")){
            GATEWAY_URL_BASE = "http://localhost:8081";
        }

        SERVICE_URL_BASE = GATEWAY_URL_BASE +"/micro-users/api";
        SERVICE_URL_FILE_MANAGER = GATEWAY_URL_BASE + "/service-file-manager";
    }


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


