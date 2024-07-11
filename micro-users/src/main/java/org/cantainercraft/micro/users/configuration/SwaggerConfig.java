package org.cantainercraft.micro.users.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.annotation.PostConstruct;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicUserApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/user/**")
                .build();
    }
//
//    @Bean
//    public OpenAPI customOpenApi(@Value("${application-description}") String appDescription,
//                                 @Value("${application-version}")String appVersion) {
//        return new OpenAPI()
//                .servers(List.of(new Server().url("http://localhost:8080")))
//                .info(new Info().title("Application API")
//                        .version(appVersion)
//                        .description(appDescription)
//                        .contact(new Contact().name("username")
//                                .email("test@gmail.com")));
//    }
}
