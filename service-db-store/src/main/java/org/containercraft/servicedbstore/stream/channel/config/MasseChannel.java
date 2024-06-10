package org.containercraft.servicedbstore.stream.channel.config;

import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicedbstore.dto.MessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
public class MasseChannel {

    @Bean
    public Consumer<MessageDTO> getMessage() {
        return System.out::println;
    }
}
