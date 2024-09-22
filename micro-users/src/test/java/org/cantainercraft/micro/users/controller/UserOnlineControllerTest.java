package org.cantainercraft.micro.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cantainercraft.micro.users.configuration.SecurityConfig;
import org.cantainercraft.micro.users.convertor.UserOnlineDTOConvertor;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.micro.users.service.impl.JwtService;
import org.cantainercraft.micro.users.service.impl.UserOnlineServiceImpl;
import org.cantainercraft.micro.users.service.impl.UserServiceDetailsImpl;
import org.cantainercraft.project.entity.users.User_Online;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


@WebMvcTest(UserOnlineController.class)
@Import({SecurityConfig.class, UserOnlineServiceImpl.class})
class UserOnlineControllerTest {
    @MockBean
    public JwtService jwtService;
    @MockBean
    public UserServiceDetailsImpl userServiceDetails;

    @MockBean
    private UserOnlineRepository repository;
    @MockBean
    public UserOnlineDTOConvertor convertor;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();



    @Test
    @WithMockUser
    void findAll_whenUsersOnlineExist_thenStatus200() throws Exception {

        Mockito.when(repository.findAll())
                .thenReturn(Collections.singletonList(new User_Online()));

        mockMvc.perform(get("/api/user_online/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void findAll_whenUsersOnlineNotExist_thenStatus200() throws Exception {
        Mockito.when(repository.findAll())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/user_online/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(0)))
                .andExpect(status().isOk());
    }

    //findById
    @Test
    @WithMockUser
    void findById_whenUserOnlineExist_thenStatus200() throws Exception {
        User_Online userOnline = new User_Online();
        userOnline.setUuid(UUID.randomUUID());

        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(Optional.of(userOnline));

        mockMvc.perform(get("/api/user_online/"+userOnline.getUuid())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(userOnline)))
                .andExpect(status().isOk());

       verify(repository,times(1)).findById(Mockito.any());
    }

    @Test
    @WithMockUser
    void findById_whenUserOnlineNotExist_thenStatus404() throws Exception {
        User_Online userOnline = new User_Online();
        userOnline.setUuid(UUID.randomUUID());

        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/user_online/"+userOnline.getUuid())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(repository,times(1)).findById(Mockito.any());
    }

    //update
    @Test
    @WithMockUser
    void update_whenUserOnlineNotExists_thenStatus200() throws Exception {
        User_Online userOnline = new User_Online();
        userOnline.setUuid(UUID.randomUUID());

        Mockito.when(repository.existsById(Mockito.any()))
                .thenReturn(true);
        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(userOnline);

        mockMvc.perform(put("/api/user_online/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userOnline)))
                .andDo(print())
                .andExpect(content().json(mapper.writeValueAsString(userOnline)))
                .andExpect(status().isOk());

        verify(repository,times(1)).save(Mockito.any());
    }

    @Test
    @WithMockUser
    void update_whenUserOnlineNotExists_thenStatus404() throws Exception {
        User_Online userOnline = new User_Online();
        userOnline.setUuid(UUID.randomUUID());

        Mockito.when(repository.existsById(Mockito.any()))
                .thenReturn(false);

        mockMvc.perform(put("/api/user_online/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userOnline)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}