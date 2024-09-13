package org.cantainercraft.micro.users.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class TemplateConfig {
    private String SERVICE_URL_FILE_MANAGER;
    @Value("${GATEWAY_URL_BASE}")
    private String GATEWAY_URL_BASE;
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

        SERVICE_URL_FILE_MANAGER = GATEWAY_URL_BASE + "/service-file-manager";
    }

    @Bean("fileClient")
    WebClient fileClient(){
        return WebClient.builder()
                .baseUrl(SERVICE_URL_FILE_MANAGER)
                .build();
    }

}
