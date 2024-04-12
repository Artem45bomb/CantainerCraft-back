package ru.micro.chats.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ru.micro.chats.data"})
@EnableDiscoveryClient
@EntityScan({"ru.weather.project.entity.chats"})
public class MicroChatsDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroChatsDataApplication.class, args);
    }

}
