package org.cantainercraft.micro.chats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"org.cantainercraft.micro.chats"})
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan({"org.cantainercraft.project.entity.chats"})
public class MicroChatsDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroChatsDataApplication.class, args);
    }

}
