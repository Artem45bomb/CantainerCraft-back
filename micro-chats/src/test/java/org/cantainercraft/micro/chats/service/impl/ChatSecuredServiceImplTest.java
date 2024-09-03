package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.repository.ChatSecuredRepository;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
class ChatSecuredServiceImplTest {

    @Mock
    private UserWebClient userClient;
    @Mock
    private ChatSecuredRepository repository;
    @Mock
    private ChatService chatService;

    @InjectMocks
    private ChatSecuredServiceImpl service;

    @Test
    void save_whenSuccess_returnChatSecured() {
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(UUID.randomUUID())
                .userId(1L)
                .build();
        Chat_Secured result = Chat_Secured.builder()
                .chat(new Chat())
                .userId(1L)
                .build();


        when(userClient.userExist(1L)).thenReturn(true);
        when(chatService.findByUUID(dto.getChatId())).thenReturn(Optional.of(new Chat()));
        when(repository.existsByUserIdAndChat(1L,new Chat())).thenReturn(false);
        when(repository.save(any())).thenReturn(result);

        Assertions.assertEquals(service.save(dto),result);
    }

    @Test
    void save_whenNotExistUserId_NotResourceException() {
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(UUID.randomUUID())
                .userId(1L)
                .build();

        when(chatService.findByUUID(any())).thenReturn(Optional.of(new Chat()));
        when(userClient.userExist(dto.getUserId())).thenReturn(false);

        Exception ex = Assertions.assertThrows(NotResourceException.class,() -> service.save(dto));
        Assertions.assertEquals(ex.getMessage(),"user is not exist");
    }

    @Test
    void save_whenNotChatExist_NotResourceException() {
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(UUID.randomUUID())
                .userId(1L)
                .build();

        when(chatService.findByUUID(any())).thenReturn(Optional.empty());

        Exception ex = Assertions.assertThrows(NotResourceException.class,() -> service.save(dto));
        Assertions.assertEquals(ex.getMessage(),"chat is not exist");
    }

    @Test
    void save_whenChatSecured_ExistResourceException() {
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(UUID.randomUUID())
                .userId(1L)
                .build();

        when(userClient.userExist(1L)).thenReturn(true);
        when(chatService.findByUUID(dto.getChatId())).thenReturn(Optional.of(new Chat()));
        when(repository.existsByUserIdAndChat(1L,new Chat())).thenReturn(true);

        Exception ex = Assertions.assertThrows(ExistResourceException.class,() -> service.save(dto));
        Assertions.assertEquals(ex.getMessage(),"chat is secured");
    }

    @Test
    void deleteById_whenExistById_Success() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(any())).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> service.deleteById(id));
    }

    @Test
    void deleteById_whenNotExistById_Success() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(any())).thenReturn(false);

        Exception ex = Assertions.assertThrows(NotResourceException.class,() -> service.deleteById(id));
        Assertions.assertEquals(ex.getMessage(),"chat secured is not exist");
    }

    @Test
    void delete_whenExistChatSecured_success() {
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(UUID.randomUUID())
                .userId(1L)
                .build();

        when(repository.existsByUserIdAndChat(dto.getUserId(),Chat.builder()
                        .uuid(dto.getChatId())
                        .build())).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> service.delete(dto));
    }

    @Test
    void delete_whenNotExistChatSecured_NotResourceException() {
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(UUID.randomUUID())
                .userId(1L)
                .build();

        when(repository.existsByUserIdAndChat(dto.getUserId(),Chat.builder()
                .uuid(dto.getChatId())
                .build())).thenReturn(false);

        Exception ex = Assertions.assertThrows(NotResourceException.class,() -> service.delete(dto));
        Assertions.assertEquals(ex.getMessage(),"chat secured is not exist");
    }

    @Test
    void findAll_whenExistEntities_returnList() {
        UUID chatId = UUID.randomUUID();
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(chatId)
                .userId(1L)
                .build();
        Chat_Secured result = Chat_Secured.builder()
                .chat(Chat.builder().uuid(chatId).build())
                .userId(1L)
                .build();


        when(repository.findAll()).thenReturn(Collections.singletonList(result));

        Assertions.assertEquals(service.findAll(),Collections.singletonList(dto));
    }

    @Test
    void findByUserId_whenExist_returnList() {
        UUID chatId = UUID.randomUUID();
        ChatSecuredDTO dto = ChatSecuredDTO.builder()
                .chatId(chatId)
                .userId(1L)
                .build();
        Chat_Secured result = Chat_Secured.builder()
                .chat(Chat.builder().uuid(chatId).build())
                .userId(1L)
                .build();


        when(repository.findByUserId(any())).thenReturn(Collections.singletonList(result));

        Assertions.assertEquals(service.findByUserId(1L),Collections.singletonList(dto));
    }
}