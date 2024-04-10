package ru.project.socket.chats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "ru.project.socket.chats")
@EntityScan("ru.weather.project.entity")
public class SocketChatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketChatsApplication.class, args);
    }

}
