package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.repository.ChatRepository;
import org.cantainercraft.micro.chats.repository.ChatSecuredRepository;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ChatSecuredServiceImplTest {

    @Mock
    private UserWebClient userClient;

    @Mock
    private ChatSecuredRepository repository;

    @Mock
    private ChatRepository chatRepository;

    @InjectMocks
    private ChatSecuredServiceImpl service;


    private Long userId;
    private Chat_Secured chatSecured;
    private UUID chatSecuredId;
    private UUID chatId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = 1L;
        chatSecuredId = UUID.randomUUID();
        chatId = UUID.randomUUID();

        Chat chat = Chat.builder().uuid(chatId).build();

        chatSecured = Chat_Secured.builder()
                .uuid(chatSecuredId)
                .userId(userId)
                .chat(chat)
                .build();
    }

    @Test
    void save_whenDoesntEntity_isNotExist() {
        ChatSecuredDTO dto = new ChatSecuredDTO();
        when(userClient.userExist(1l)).thenReturn(false);

        NotResourceException exception = assertThrows(NotResourceException.class, () -> {
            service.save(dto);
        });

        assertEquals("user is not exist", exception.getMessage());
    }

    @Test
    void deleteById_whenEntity_delete() {
        Chat_Secured chat_secured = new Chat_Secured();
        UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenReturn(Optional.of(chat_secured));

        service.deleteById(uuid);

        verify(repository).deleteById(uuid);
    }

    @Test
    void deleteById_whenEntity_notExist() {
        UUID uuid = UUID.randomUUID();

        when(repository.existsById(any())).thenReturn(false);

        assertThrows(NotResourceException.class, () -> service.deleteById(uuid));
    }

    @Test
    void delete_whenEntity_notExist() {
        Chat chat = new Chat();
        ChatSecuredDTO chatSecuredDTO = new ChatSecuredDTO();
        when(repository.existsByUserIdAndChat(1l,chat)).thenReturn(false);

        assertThrows(NotResourceException.class, () -> service.delete(chatSecuredDTO));
    }

    @Test
    void delete_whenEntity_exist() {
        Chat chat = new Chat();
        ChatSecuredDTO chatSecuredDTO = new ChatSecuredDTO();
        when(repository.existsByUserIdAndChat(userId, chat)).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> service.delete(chatSecuredDTO));
    }

    @Test
    void findAll_whenEntity_List() {
        when(repository.findAll()).thenReturn(Collections.singletonList(new Chat_Secured()));

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void findByUserId_whenEntity_chatSecured() {
        when(repository.findByUserId(userId)).thenReturn(List.of(chatSecured));

        List<ChatSecuredDTO> result = service.findByUserId(userId);

        assertEquals(1, result.size());
    }

    @Test
    void findByUserId_whenEntityDoesntExist_() {
        when(repository.findByUserId(userId)).thenReturn(List.of());

        List<ChatSecuredDTO> result = service.findByUserId(userId);

        assertTrue(result.isEmpty());
    }
}