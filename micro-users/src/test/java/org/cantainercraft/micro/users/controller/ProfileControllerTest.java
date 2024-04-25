package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.users.service.impl.ProfileServiceImpl;
import org.cantainercraft.project.entity.Profile;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import java.util.UUID;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {
    private final Gson gson = new Gson();
    @MockBean
    public ProfileServiceImpl profileService;

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void givenProfileById_whenExistProfile_thenStatus200() throws Exception{
        UUID uuid = UUID.randomUUID();
        Profile profileTest = new Profile();
        profileTest.setUuid(uuid);

        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.of(profileTest));

        mockMvc.perform(post("/profile/id")
                .contentType("application/json")
                .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(profileTest)));
    }

    @Test
    public void givenProfileById_whenNotExistProfile_thenStatus404() throws Exception{
        UUID uuid = UUID.randomUUID();

        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/profile/id")
                        .contentType("application/json")
                        .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    @Test
    public void givenProfileByUser_whenExistProfile_thenStatus200() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"cond@gmail.com");
        Profile profile = Profile
                .builder()
                .uuid(UUID.randomUUID())
                .build();

        Mockito.when(profileService.findByUser(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.of(profile));

        mockMvc.perform(post("/profile/user")
                .contentType("application/json")
                .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(profile)));
    }

    @Test
    public void givenProfileByUser_whenNotExistProfile_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"cond@gmail.com");

        Mockito.when(profileService.findByUser(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/profile/user")
                        .contentType("application/json")
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    @Test
    public void givenProfileByUser_whenNotValidateEmail_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"");

        Mockito.when(profileService.findByUser(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/profile/user")
                        .contentType("application/json")
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("param missed: email"))));
    }

    @Test
    public void givenProfileByUser_whenNotValidateId_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(0L,"cond@gmail.com");

        Mockito.when(profileService.findByUser(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/profile/user")
                        .contentType("application/json")
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("param missed: userId"))));
    }

    @Test
    public void saveProfile_whenNotExistProfile_thenStatus201() throws Exception {
        Profile profileRequest = Profile.builder()
                .about("I programming")
                .build();
        Profile profileResponse = Profile.builder()
                .uuid(UUID.randomUUID())
                .build();

        Mockito.when(profileService.save(Mockito.any()))
                .thenReturn(profileResponse);

        mockMvc.perform(post("/profile/add")
                .contentType("application/json")
                .content(gson.toJson(profileRequest)))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(content().json(gson.toJson(profileResponse)));
    }

    @Test
    public void saveProfile_whenExistProfile_thenStatus409() throws Exception{
        Profile profileRequest = Profile.builder()
                .about("I programming")
                .build();
        Profile profileResponse = Profile.builder()
                .uuid(UUID.randomUUID())
                .build();

        Mockito.when(profileService.findByUser(Mockito.any(),Mockito.any()))
                .thenReturn(Optional.of(profileResponse));

        mockMvc.perform(post("/profile/add")
                        .contentType("application/json")
                        .content(gson.toJson(profileRequest)))
                .andDo(print())
                .andExpect(status().is(409))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is exist"))));
    }

    @Test
    public void updateProfile_whenExistProfile_thenStatus200() throws Exception{
        Profile profileRequest = Profile.builder()
                .uuid(UUID.randomUUID())
                .about("I programming")
                .build();
        Profile profileFind = Profile.builder()
                .uuid(UUID.randomUUID())
                .build();

        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.of(profileFind));
        Mockito.when(profileService.update(Mockito.any()))
                .thenReturn(profileRequest);

        mockMvc.perform(put("/profile/update")
                        .contentType("application/json")
                        .content(gson.toJson(profileRequest)))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void updateProfile_whenNotExistProfile_thenStatus406() throws Exception{
        Profile profileRequest = Profile.builder()
                .uuid(UUID.randomUUID())
                .about("I programming")
                .build();

        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/profile/update")
                        .contentType("application/json")
                        .content(gson.toJson(profileRequest)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    @Test
    public void deleteById_whenExistProfile_thenStatus200() throws Exception{
        Profile profileRequest = Profile.builder()
                .uuid(UUID.randomUUID())
                .about("I programming")
                .build();

        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.of(profileRequest));

        mockMvc.perform(put("/profile/delete/id")
                        .contentType("application/json")
                        .content(gson.toJson(UUID.randomUUID())))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void deleteById_whenNotExistProfile_thenStatus406() throws Exception{
        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/profile/delete/id")
                        .contentType("application/json")
                        .content(gson.toJson(UUID.randomUUID())))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("profile is not exist"))));
    }

    @Test
    public void deleteByUser_whenExistProfile_thenStatus200() throws Exception{
        Profile profileRequest = Profile.builder()
                .uuid(UUID.randomUUID())
                .about("I programming")
                .build();

        Mockito.when(profileService.findById(Mockito.any()))
                .thenReturn(Optional.of(profileRequest));

        mockMvc.perform(put("/profile/delete/id")
                        .contentType("application/json")
                        .content(gson.toJson(UUID.randomUUID())))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void deleteProfileByUser_whenNotValidateEmail_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(1L,"");

        mockMvc.perform(put("/profile/delete/user")
                        .contentType("application/json")
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("param missed: email"))));
    }

    @Test
    public void deleteProfileByUser_whenNotValidateId_thenStatus406() throws Exception{
        ProfileSearchDTO profileSearchDTO = new ProfileSearchDTO(0L,"cond@gmail.com");

        mockMvc.perform(put("/profile/delete/user")
                        .contentType("application/json")
                        .content(gson.toJson(profileSearchDTO)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("param missed: userId"))));
    }
}

