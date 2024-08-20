package org.cantainercraft.micro.chats.configuration;

import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {
    @Bean
    @Primary
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean(name = "stream-mapper-message")
    public ModelMapper streamMapperMessage(){
        var mapper = new ModelMapper();
        return mapper;
    }

    @Bean(name = "chat-secured")
    public ModelMapper chatSecuredMapper(){
        var mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap< Chat_Secured,ChatSecuredDTO>() {
            protected void configure(){
                skip(destination.getChatId());
            }
        });
        mapper.addMappings(new PropertyMap<ChatSecuredDTO,Chat_Secured>() {
            @Override
            protected void configure(){
                skip(destination.getChat());
            }
        });
        return mapper;
    }
}
