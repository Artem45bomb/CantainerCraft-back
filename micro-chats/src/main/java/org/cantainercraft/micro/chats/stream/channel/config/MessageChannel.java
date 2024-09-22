package org.cantainercraft.micro.chats.stream.channel.config;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import org.cantainercraft.micro.chats.dto.stream.MessageChannelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


@Configuration
@Setter
@Getter
@Hidden
public class MessageChannel {

    private final ModelMapper mapper;

    public MessageChannel(@Qualifier("stream-mapper-message") ModelMapper mapper){
        this.mapper = mapper;
    }

   @Bean
   public Function<MessageChannelDTO,MessageChannelDTO> submitMessage(){
       return (messageDTO) -> messageDTO;
   }

}
