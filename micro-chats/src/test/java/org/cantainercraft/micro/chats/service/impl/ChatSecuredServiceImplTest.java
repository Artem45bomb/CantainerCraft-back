package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.repository.ChatRepository;
import org.cantainercraft.micro.chats.repository.ChatSecuredRepository;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ChatSecuredServiceImplTest {

    private UserWebClient userClient;
    private ChatSecuredRepository repository;
    private ChatRepository chatRepository;

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByUserId() {
    }
}