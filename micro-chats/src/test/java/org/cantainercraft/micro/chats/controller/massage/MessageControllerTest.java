package org.cantainercraft.micro.chats.controller.massage;


import org.cantainercraft.micro.chats.configuration.SpringSecurity;
import org.cantainercraft.micro.chats.configuration.filter.JwtAuthFilter;
import org.cantainercraft.micro.chats.configuration.filter.ServiceAuthHandler;
import org.cantainercraft.micro.chats.controller.MessageController;
import org.cantainercraft.micro.chats.dto.MessageDTO;
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
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.io.IOException;
import java.util.*;

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
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;


    //base bean for filter
    @MockBean
    private UserDetailsService details;
    @MockBean
    private JwtService jwtService;
    //end base bean for filter.These bins must be present

    @MockBean
    private MessageService messageService;

    @MockBean
    private UserWebClient userWebClient;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})// authentication user with admin role
    public void findAll_whenMessageNotExist_shouldReturnEmptyList() throws Exception {
        when(messageService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/api/message/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(messageService, times(1)).findAll();
    }

    @Test
    public void findByUUID_whenMessageNotFound_shouldReturnNotResourceException() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(messageService.findByUuid(uuid)).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/message/uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uuid\":\"" + uuid + "\"}"))
                .andExpect(status().isNotFound()); // Assuming NotResourceException returns 404

        verify(messageService, times(1)).findByUuid(uuid);
    }

    @Test
    public void findByUserId_whenMessagesExist_shouldReturnListOfMessages() throws Exception {
        Long userId = 1L;
        List<Message> messages = Collections.singletonList(new Message());
        when(messageService.findByUserId(userId)).thenReturn(messages);

        mockMvc.perform(post("/api/message/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1))); // Assuming 1 message is found

        verify(messageService, times(1)).findByUserId(userId);
    }

    @Test
    public void save_whenUserDoesNotExist_shouldReturnNotResourceException() throws Exception {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserId(100L); // Non-existing user id
        when(userWebClient.userExist(anyLong())).thenReturn(false);

        mockMvc.perform(post("/api/message/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":100}"))
                .andExpect(status().isNotFound()); // Assuming NotResourceException returns 404

        verify(messageService, never()).save(any());
    }

    @Test
    public void update_whenMessageExists_shouldReturnUpdatedMessage() throws Exception {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUuid(UUID.randomUUID());
        messageDTO.setUserId(1L);
        Message updatedMessage = new Message();
        when(messageService.update(messageDTO)).thenReturn(updatedMessage);
        when(userWebClient.userExist(anyLong())).thenReturn(true);

        mockMvc.perform(put("/api/message/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uuid\":\"" + messageDTO.getUuid() + "\", \"userId\":1}"))
                .andExpect(status().isOk());

        verify(messageService, times(1)).update(messageDTO);
    }

    @Test
    public void delete_whenMessageExists_shouldReturnNoContent() throws Exception {
        UUID uuid = UUID.randomUUID();



        mockMvc.perform(put("/api/message/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uuid\":\"" + uuid + "\"}"))
                .andExpect(status().isNoContent());

        verify(messageService, times(1)).delete(uuid);
    }

}
