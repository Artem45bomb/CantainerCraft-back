package org.containercraft.servicedbstore.stream.channel.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicedbstore.convertor.MessageDTOConvertor;
import org.containercraft.servicedbstore.dto.MessageDTO;
import org.containercraft.servicedbstore.service.GrpcMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MasseChannel {
    private final MessageDTOConvertor convertor;
    private final GrpcMessageService grpcMessageService;

    @Bean
    public Consumer<MessageDTO> getMessage() {
        return (messageDTO) -> {
            grpcMessageService.sendMessage(convertor.convertDTOToEntity(messageDTO));
        };
    }
}
