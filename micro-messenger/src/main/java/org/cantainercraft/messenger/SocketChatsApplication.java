package org.cantainercraft.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "ru.project.socket.chats")
@EnableFeignClients(basePackages = "ru.project.socket.chats.feign")
public class SocketChatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketChatsApplication.class, args);
    }

}
