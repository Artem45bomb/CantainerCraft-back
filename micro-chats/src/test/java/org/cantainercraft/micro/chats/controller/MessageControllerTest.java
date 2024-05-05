package org.cantainercraft.micro.chats.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.chats.convertor.MessageDTOConvertor;
import org.cantainercraft.micro.chats.dto.MessageSearchDTO;
import org.cantainercraft.micro.chats.controller.MessageController;
import org.cantainercraft.micro.chats.feign.UserFeignClient;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.chats.service.impl.MessageServiceImpl;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.User;
import org.cantainercraft.project.entity.chats.Message;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;
import java.util.stream.Collectors;
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    private final Gson gson = new Gson();

    @MockBean
    MessageServiceImpl messageService;

    @MockBean
    public UserFeignClient userFeignClient;

    private final MessageDTOConvertor convertor = new MessageDTOConvertor(new ModelMapper());

    @Autowired
    MockMvc mockMvc;

    @Test
    public void givenMessageByUUID_whenMessageExistChat_thenStatus200() throws Exception {
        UUID uuid = UUID.randomUUID();
        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.of(messageTest));

        mockMvc.perform(post("/message/uuid")
                        .contentType("application/json")
                        .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void givenMessageByUUID_whenMessageNotExistChat_thenStatus404() throws Exception {
        UUID uuid =  UUID.randomUUID();
        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(post("/message/uuid")
                        .contentType("application/json")
                        .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotResourceException.class));
    }
    @Test
    public void deleteMessageByUUID_whenMessageExist_thenStatus200() throws Exception {
        UUID uuid =  UUID.randomUUID();
        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.of(messageTest));

        mockMvc.perform(put("/message/delete")
                        .contentType("application/json")
                        .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().is(200));
    }
    @Test
    public void deleteMessageByUUID_whenMessageNotExist_thenStatus404() throws Exception {
        UUID uuid =  UUID.randomUUID();
        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/message/delete")
                        .contentType("application/json")
                        .content(gson.toJson(uuid)))
                .andDo(print())
                .andExpect(status().is(404));
    }
    @Test
    public void saveMessage_whenMessageExist_thenStatus409() throws Exception {
        UUID uuid =  UUID.randomUUID();
        long user_id = 1;

        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        User userTest = User.builder()
                .id(user_id)
                .build();

        Mockito.when(userFeignClient.userExist(Mockito.any())).thenReturn(ResponseEntity.ok().body(userTest));
        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.of(messageTest));

        mockMvc.perform(post("/message/add")
                        .contentType("application/json")
                        .content(gson.toJson(messageTest)))
                .andDo(print())
                .andExpect(status().is(409))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(ExistResourceException.class));
    }
    @Test
    public void saveMessage_whenMessageNotExist_thenStatus200() throws Exception {
        UUID uuid =  UUID.randomUUID();
        long user_id = 1;

        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        User userTest = User.builder()
                .id(user_id)
                .build();

        Mockito.when(userFeignClient.userExist(Mockito.any())).thenReturn(ResponseEntity.ok().body(userTest));
        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(post("/message/add")
                        .contentType("application/json")
                        .content(gson.toJson(messageTest)))
                .andDo(print())
                .andExpect(status().is(200));
    }
    @Test
    void updateMessage_whenMessageExist_thenStatus200() throws Exception {
        UUID uuid =  UUID.randomUUID();
        long user_id = 1;

        Message messageRequest = Message.builder()
                .uuid(uuid)
                .userId(user_id)
                .build();

        Message messageTest = Message.builder()
                .uuid(uuid)
                .userId(user_id)
                .build();

        User userTest = User.builder()
                .id(user_id)
                .build();

        Mockito.when(userFeignClient.userExist(Mockito.any())).thenReturn(ResponseEntity.ok().body(userTest));
        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.of(messageTest));

        mockMvc.perform(put("/message/update")
                        .contentType("application/json")
                        .content(gson.toJson(convertor.convertMessageToMessageDTO(messageRequest))))
                .andDo(print())
                .andExpect(status().is(200));
    }
    @Test
    void updateMessage_whenMessageNotExist_thenStatus404() throws Exception {
        UUID uuid =  UUID.randomUUID();
        long user_id = 1;

        Message messageRequest = Message.builder()
                .uuid(uuid)
                .userId(user_id)
                .build();

        User userTest = User.builder()
                .id(user_id)
                .build();

        Mockito.when(userFeignClient.userExist(Mockito.any())).thenReturn(ResponseEntity.ok().body(userTest));
        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/message/update")
                        .contentType("application/json")
                        .content(gson.toJson(convertor.convertMessageToMessageDTO(messageRequest))))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NotResourceException.class));
    }
    @Test
    void updateMessage_withMissedParam_thenStatus406() throws Exception {
        long user_id = 1;
        Message messageRequest = Message.builder()
                .uuid(null)
                .build();

        UUID uuid =  UUID.randomUUID();
        Message messageTest = Message.builder()
                .uuid(uuid)
                .build();

        User userTest = User.builder()
                .id(user_id)
                .build();

        Mockito.when(userFeignClient.userExist(Mockito.any())).thenReturn(ResponseEntity.ok().body(userTest));
        Mockito.when(messageService.findByUUID(Mockito.any())).thenReturn(Optional.of(messageTest));

        mockMvc.perform(put("/message/update")
                        .contentType("application/json")
                        .content(gson.toJson(messageRequest)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("Missed param: id"))));;
    }


}
