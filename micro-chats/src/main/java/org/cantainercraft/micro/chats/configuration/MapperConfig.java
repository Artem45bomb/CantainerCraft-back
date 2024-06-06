package org.cantainercraft.micro.chats.configuration;

import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.chats.dto.stream.MessageChannelDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean(name = "stream-mapper-message")
    public ModelMapper streamMapperMessage(){
        var mapper = new ModelMapper();
//        mapper.addMappings(new PropertyMap<MessageDTO, MessageChannelDTO> (){
//            @Override
//            protected void configure() {
//                skip(destination.getChatId());
//            }
//        });

        return mapper;
    }
}
