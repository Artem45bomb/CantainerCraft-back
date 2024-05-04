package org.cantainercraft.micro.chats.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.chats.convertor.ChatDTOConvertor;
import org.cantainercraft.micro.chats.feign.UserFeignClient;
import org.cantainercraft.micro.chats.service.impl.ChatServiceImpl;
import org.cantainercraft.micro.chats.service.impl.UserChatServiceImpl;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.User_Chat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;
@WebMvcTest(ChatController.class)
public class ChatControllerTest {
        private final Gson gson = new Gson();
        @MockBean
        public ChatServiceImpl chatService;

        @MockBean
        public UserChatServiceImpl userChatService;

        private final ChatDTOConvertor convertor = new ChatDTOConvertor(new ModelMapper());

        @Autowired
        public MockMvc mockMvc;

        @Test
        public void givenChatByUUID_whenGetExistChat_thenStatus200() throws Exception {
        UUID uuid = UUID.randomUUID();
                Chat chatTest = new Chat();
                chatTest.setUuid(uuid);

                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.of(chatTest));

                mockMvc.perform(post("/chat/uuid")
                                .contentType("application/json")
                                .content(gson.toJson(uuid)))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().json(gson.toJson(chatTest)));
        }
        @Test
        public void givenChatByUUID_whenNotExistChat_thenStatus404() throws Exception {
                UUID uuid = UUID.randomUUID();
                Chat chatTest = new Chat();
                chatTest.setUuid(uuid);
                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.empty());

                mockMvc.perform(post("/chat/id")
                                .contentType("application/json")
                                .content(gson.toJson(uuid)))
                        .andDo(print())
                        .andExpect(status().is(404));
        }
        @Test
        public void deleteChatByName_whenChatExist_thenStatus406() throws Exception {
                Chat chatTest = Chat.builder()
                        .uuid(UUID.randomUUID())
                        .name(null)
                        .build();

                Mockito.when(chatService.findByName(Mockito.any()))
                        .thenThrow(new NotValidateParamException("Missed param: name"));

                mockMvc.perform(put("/chat/delete/name")
                                .contentType("application/json")
                                .content(gson.toJson(null)))
                        .andDo(print())
                        .andExpect(status().is(406));
        }
        @Test
        public void deleteChatByUUID_whenChatExist_thenStatus200() throws Exception {
                UUID uuid = UUID.randomUUID();
                Chat chatTest = new Chat();
                chatTest.setUuid(uuid);

                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.of(chatTest));

                mockMvc.perform(put("/chat/delete")
                                .contentType("application/json")
                                .content(gson.toJson(uuid)))
                        .andDo(print())
                        .andExpect(status().is(200));
        }
        @Test
        public void updateChat_whenChatNotExist_thenStatus404() throws Exception {
                UUID uuid = UUID.randomUUID();
                Chat chatTest = new Chat();
                chatTest.setUuid(uuid);

                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.empty());

                mockMvc.perform(put("/chat/update")
                        .contentType("application/json")
                        .content(gson.toJson(chatTest)))
                        .andDo(print())
                        .andExpect(status().is(404));
        }
        @Test
        public void updateChat_whenChatExist_thenStatus200() throws Exception {
                UUID uuid = UUID.randomUUID();

                Chat chatRequest = Chat.builder()
                        .uuid(uuid)
                        .name("Dima")
                        .build();

                Chat chatTest = Chat.builder()
                        .uuid(uuid)
                        .build();

                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.of(chatTest));

                mockMvc.perform(put("/chat/update")
                                .contentType("application/json")
                                .content(gson.toJson(chatTest)))
                        .andDo(print())
                        .andExpect(status().is(200));
        }
        @Test
        void updateChat_withMissedParam_thenStatus406() throws Exception {
                Chat chatRequest = Chat.builder()
                        .uuid(null)
                        .name("Dima")
                        .build();

                UUID uuid = UUID.randomUUID();
                Chat chatTest = new Chat();
                chatTest.setUuid(uuid);

                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.of(chatTest));

                mockMvc.perform(put("/chat/update")
                                .contentType("application/json")
                                .content(gson.toJson(chatRequest)))
                        .andDo(print())
                        .andExpect(status().is(406))
                        .andExpect(content().json(gson.toJson(MessageError.of("missed param: id"))));
        }
        @Test
        void saveChat_whenChatExist_thenStatus409() throws Exception {
                Chat chatRequest = Chat.builder()
                        .name("Dima")
                        .build();

                Chat chatResponse = Chat.builder()
                        .uuid(UUID.randomUUID())
                        .name("Dima")
                        .build();

                Mockito.when(chatService.findByName(Mockito.any()))
                        .thenReturn(Optional.of(chatResponse));

                mockMvc.perform(post("/chat/add")
                                .contentType("application/json")
                                .content(gson.toJson(chatRequest)))
                        .andDo(print())
                        .andExpect(status().is(409))
                        .andExpect(content().json(gson.toJson(MessageError.of("chat is exist"))));
        }
        @Test
        void saveChat_whenChatNotExist_thenStatus200() throws Exception {
                Chat chatRequest = Chat.builder()
                        .uuid(UUID.randomUUID())
                        .name("Dima")
                        .build();

                Mockito.when(chatService.findByUUID(Mockito.any()))
                        .thenReturn(Optional.empty());

                mockMvc.perform(post("/chat/add")
                                .contentType("application/json")
                                .content(gson.toJson(convertor.convertChatToChatDTO(chatRequest))))
                        .andDo(print())
                        .andExpect(status().is(200));
        }

        @Test
        void search_whenUserExists_thenStatus200() throws Exception {
        long user_id = 1;

                Chat chatRequest = Chat.builder()
                        .uuid(UUID.randomUUID())
                        .name("Dima")
                        .build();

                User_Chat userChatTest = User_Chat.builder()
                        .chat(chatRequest)
                        .userId(user_id)
                        .build();

                List<User_Chat> user_chats = new ArrayList<>();
                user_chats.add(userChatTest);

                Mockito.when(userChatService.findBySearch(Mockito.any(), Mockito.any(), Mockito.any()))
                        .thenReturn(user_chats);

                mockMvc.perform(post("/chat/user/search")
                                .contentType("application/json")
                                .content(gson.toJson(user_id)))
                        .andDo(print())
                        .andExpect(status().is(200));
        }

}
