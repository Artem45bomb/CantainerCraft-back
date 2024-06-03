package org.containercraft.servicedbstore.stream.channel.consumer;


import org.containercraft.servicedbstore.dto.MessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class MessageConfigChannel {

    @Bean
    public Consumer<MessageDTO> getMessage(){
       return messageDTO -> {
           System.out.println(messageDTO);
       };
    }

    @Bean
    public Function<String,String> processMessage(){
        return message -> "service-db-store:"+message;
    }
}
