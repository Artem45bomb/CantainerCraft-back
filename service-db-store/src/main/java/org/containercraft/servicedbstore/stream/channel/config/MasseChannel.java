package org.containercraft.servicedbstore.stream.channel.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
public class MasseChannel {

    @Bean
    public Function<String,String> processMessage(){
        return messageDTO -> {
            log.info(messageDTO);
            return messageDTO;
        };
    }

//    @Bean
//    public Consumer<String> getMessage(){
//        return System.out::println;
//    }
}
