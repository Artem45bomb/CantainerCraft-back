package org.cantainercraft.micro.users.reactive.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class ConsumerFunc {
    @Bean
    public Consumer<Message<Long>> testUserGet(){
        return message -> {
            System.out.println(message.getPayload());
        };
    }
}
