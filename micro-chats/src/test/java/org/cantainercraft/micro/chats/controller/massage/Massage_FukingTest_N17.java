package org.cantainercraft.micro.chats.controller.massage;


import org.cantainercraft.micro.chats.configuration.SpringSecurity;
import org.cantainercraft.micro.chats.configuration.filter.JwtAuthFilter;
import org.cantainercraft.micro.chats.configuration.filter.ServiceAuthHandler;
import org.cantainercraft.micro.chats.controller.MessageController;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.chats.service.impl.JwtService;
import org.cantainercraft.micro.chats.service.impl.UserDetailServiceImpl;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.project.entity.chats.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;



@WebMvcTest(
        value=MessageController.class
)
@Import(SpringSecurity.class)
public class Massage_FukingTest_N17 {



}
