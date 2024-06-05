package org.cantainercraft.micro.chats.stream.channel.config;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Function;

@Configuration
public class MessageChannel {

    @Bean
    public Function<String,String> processMessage(){
        return (message) -> {
            System.out.println(message);
            return message;
        };
    }



}
