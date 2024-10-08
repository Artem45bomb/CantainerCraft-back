package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.UserChatDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.repository.UserChatRepository;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.User_Chat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserChatServiceImplTest {
    @Mock
    public UserChatRepository repository;
    @Mock
    public UserChatDTOConvertor convertor;
    @Mock
    public UserWebClient userWebClient;
    @InjectMocks
    public UserChatServiceImpl service;

    @Test
    @Tag("save")
    void save_whenUserNotExist_Exception(){
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).build();
        User_Chat result = new User_Chat(1L,2L,null);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(userWebClient.userExist(2L)).thenReturn(false);
        Exception ex = assertThrows(Exception.class, () -> service.save(dto));
        assertEquals(ex.getMessage(), "user is not exist");

    }

    @Test
    @Tag("save")
    void save_whenSuccess_Entity(){
        UserChatDTO dto = UserChatDTO
                .builder()
                .id(1L)
                .userId(2L)
                .chat(ChatDTO.builder().uuid(UUID.randomUUID()).build())
                .build();
        User_Chat result = new User_Chat(1L,2L,null);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.save(any())).thenReturn(result);
        when(userWebClient.userExist(2L)).thenReturn(true);
        assertEquals(service.save(dto),result);
    }

    @Test
    @Tag("deleteByUserId")
    void deleteByUserId_whenIdNotExist_Exception(){
        UUID id = UUID.randomUUID();
        assertThrows(NotResourceException.class,()->service.deleteByUserId(1L,id));
    }

    @Test
    @Tag("deleteByUserId")
    void deleteByUserId_whenSuccess_Success(){
        UUID id = UUID.randomUUID();
        when(repository.existsByUserIdAndChatUuid(1L,id)).thenReturn(true);
        Assertions.assertDoesNotThrow(()->service.deleteByUserId(1L,id));
    }

    @Test
    @Tag("deleteById")
    void deleteById_whenIdNotExist_Exception(){
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).build();
        when(repository.existsById(1L)).thenReturn(false);
        assertThrows(NotResourceException.class,()->service.deleteById(dto.getId()));
    }

    @Test
    @Tag("deleteById")
    void deleteById_whenSuccess_Success(){
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).build();
        when(repository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(()->service.deleteById(dto.getId()));
    }

    @Test
    @Tag("findById")
    void findById_whenIdNotExist_OptionalEmpty(){
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).build();
        when(repository.existsById(any())).thenReturn(false);
        assertEquals(service.findById(dto.getId()), Optional.empty());
    }

    @Test
    @Tag("findById")
    void findById_whenSuccess_OptionalEntity(){
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).build();
        when(repository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(()->service.findById(dto.getId()));
    }

    @Test
    @Tag("findBySearch")
    void findBySearch_whenChatIdNotExist_Nothing(){
        UUID id = UUID.randomUUID();
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).chat(ChatDTO.builder().uuid(id).build()).build();
        when(repository.existsById(any())).thenReturn(false);
        assertEquals(service.findBySearch(dto.getUserId(), dto.getChat().getUuid()),List.of());
    }

    @Test
    @Tag("findBySearch")
    void findBySearch_whenUserIdNotExist_ListEmpty(){
        UUID id = UUID.randomUUID();
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).chat(ChatDTO.builder().uuid(id).build()).build();
        when(repository.existsById(any())).thenReturn(true);
        when(repository.existsByUserIdAndChatUuid(dto.getUserId(),dto.getChat().getUuid())).thenReturn(false);
        assertEquals(service.findBySearch(dto.getUserId(), dto.getChat().getUuid()),List.of());
    }

    @Test
    @Tag("findBySearch")
    void findBySearch_whenSuccess_ListEntity(){
        UUID id = UUID.randomUUID();
        UserChatDTO dto = UserChatDTO.builder().id(1L).userId(2L).chat(ChatDTO.builder().uuid(id).build()).build();
        when(repository.existsById(any())).thenReturn(true);
        when(repository.existsByUserIdAndChatUuid(dto.getUserId(),dto.getChat().getUuid())).thenReturn(true);
        assertDoesNotThrow(()-> service.findBySearch(dto.getUserId(), dto.getChat().getUuid()));
    }
    @Test
    @Tag("findAll")
    void findAll_success_ListEntity(){
        User_Chat result = new User_Chat();
        when(repository.findAll()).thenReturn(List.of(result));
        assertEquals(service.findAll(),List.of(result));
    }
    @Test
    @Tag("findAll")
    void findAll_success_ListEmpty(){
        when(repository.findAll()).thenReturn(List.of());
        assertEquals(service.findAll(),List.of());
    }
}