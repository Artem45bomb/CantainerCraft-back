package org.containercraft.servicefilemanager;

import org.containercraft.servicefilemanager.config.StorageProperties;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ServiceFileManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
