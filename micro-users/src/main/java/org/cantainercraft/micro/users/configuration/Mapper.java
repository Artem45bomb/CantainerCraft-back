package org.cantainercraft.micro.users.configuration;

import org.cantainercraft.micro.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Mapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
