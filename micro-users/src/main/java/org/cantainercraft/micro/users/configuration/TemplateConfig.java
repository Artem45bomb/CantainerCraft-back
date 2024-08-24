package org.cantainercraft.micro.users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TemplateConfig {
    private static final String SERVICE_URL_FILE_MANAGER= "http://localhost:8081/service-file-manager";


    @Bean("fileClient")
    WebClient fileClient(){
        return WebClient.builder()
                .baseUrl(SERVICE_URL_FILE_MANAGER)
                .build();
    }

}
