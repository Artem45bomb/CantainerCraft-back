package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.users.configuration.SecurityConfig;
import org.cantainercraft.micro.users.convertor.ProfileDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.users.repository.ProfileRepository;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.impl.JwtService;
import org.cantainercraft.micro.users.service.impl.UserServiceDetailsImpl;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.users.service.impl.ProfileServiceImpl;
import org.cantainercraft.project.entity.users.Profile;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@WebMvcTest(ProfileController.class)
@Import({SecurityConfig.class,ProfileServiceImpl.class})
public class ProfileControllerTest {
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserServiceDetailsImpl userDetailsService;
    private final Gson gson = new Gson();
    @MockBean
    public ProfileRepository repository;
    @MockBean
    public ProfileDTOConvertor convertor;
    @MockBean
    public UserRepository userRepository;

    @Autowired
    public MockMvc mockMvc;

    //findById
    @Test
    @WithMockUser
    public void givenProfileById_whenExistProfile_thenStatus200() throws Exception{
        UUID uuid = UUID.randomUUID();
        Profile profileTest = new Profile();
        profileTest.setUuid(uuid);

        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(Optional.of(profileTest));

        mockMvc.perform(post("/api/profile/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void givenProfileById_whenNotExistProfile_thenStatus404() throws Exception{
        UUID uuid = UUID.randomUUID();

        Mockito.when(repository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/api/profile/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    //findByUser
    @Test
    @WithMockUser
    public void givenProfileByUser_whenExistProfile_thenStatus200() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"cond@gmail.com");
        Profile profile = Profile
                .builder()
                .uuid(UUID.randomUUID())
                .build();

        Mockito.when(repository.findByUserIdOrUserEmail(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.of(profile));

        mockMvc.perform(post("/api/profile/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(profile)));
    }

    @Test
    @WithMockUser
    public void givenProfileByUser_whenNotExistProfile_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"cond@gmail.com");

        Mockito.when(repository.findByUserIdOrUserEmail(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/api/profile/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    @Test
    @WithMockUser
    public void givenProfileByUser_whenNotValidateEmail_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"");

        mockMvc.perform(post("/api/profile/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("param missed: email"))));
    }

    @Test
    @WithMockUser
    public void givenProfileByUser_whenNotValidateId_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(0L,"cond@gmail.com");

        mockMvc.perform(post("/api/profile/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("param missed: userId"))));
    }

    //update
    @Test
    @WithMockUser
    public void updateProfile_whenExistProfile_thenStatus200() throws Exception{
        Profile profileRequest = Profile.builder()
                .uuid(UUID.randomUUID())
                .about("I programming")
                .build();

        Mockito.when(repository.existsById(Mockito.any()))
                .thenReturn(true);
        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(profileRequest);

        mockMvc.perform(put("/api/profile/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +"\"uuid\":\"a24e9b34-a3ea-4f26-831f-251fb150213d\"," +
                                "\"sunsetTime\":\"2024-08-28T14:55:57.901+00:00\"" +
                                "}"))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser
    public void updateProfile_whenNotExistProfile_thenStatus406() throws Exception{
        Mockito.when(repository.existsById(Mockito.any()))
                .thenReturn(false);

        mockMvc.perform(put("/api/profile/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +"\"uuid\":\"a24e9b34-a3ea-4f26-831f-251fb150213d\"," +
                                "\"sunsetTime\":\"2024-08-28T14:55:57.901+00:00\"" +
                                "}"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    //findAll

    @Test
    @WithMockUser
    public void findAll_whenProfilesExists_thenStatus200() throws Exception{
        Profile profile = new Profile();

        Mockito.when(repository.findAll())
                .thenReturn(Collections.singletonList(profile));

        mockMvc.perform(get("/api/profile/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    @WithMockUser
    public void findAll_whenProfilesNotExists_thenStatus200() throws Exception{
        Mockito.when(repository.findAll())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/profile/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }


}

