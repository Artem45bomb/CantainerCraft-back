package org.cantainercraft.micro.chats;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import reactor.core.publisher.Mono;

@SpringBootApplication(scanBasePackages = {"org.cantainercraft.micro.chats"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableRabbit
@EntityScan({"org.cantainercraft.project.entity.chats"})
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class MicroChatsDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroChatsDataApplication.class, args);
    }

}
