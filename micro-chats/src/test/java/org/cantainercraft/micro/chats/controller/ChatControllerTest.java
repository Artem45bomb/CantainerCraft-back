package org.cantainercraft.micro.chats.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.chats.dto.ChatSearchDTO;
import org.cantainercraft.micro.chats.controller.ChatController;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.service.impl.ChatServiceImpl;
import org.cantainercraft.project.entity.chats.Chat;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;
import java.util.stream.Collectors;
@WebMvcTest(ChatController.class)
public class ChatControllerTest {
        private final Gson gson = new Gson();
        @MockBean
        public ChatServiceImpl chatService;

        @Autowired
        public MockMvc mockMvc;
}
