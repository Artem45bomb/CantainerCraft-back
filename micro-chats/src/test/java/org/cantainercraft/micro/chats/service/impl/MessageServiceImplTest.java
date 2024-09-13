package org.cantainercraft.micro.chats.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.cantainercraft.micro.chats.convertor.MessageDTOConvertor;
import org.cantainercraft.micro.chats.repository.MessageRepository;
import org.cantainercraft.micro.chats.dto.MessageDTO;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class MessageServiceImplTest {

    @Mock
    public MessageRepository repository;
    @Mock
    public MessageDTOConvertor convertor;
    @InjectMocks
    public MessageServiceImpl service;

    @Test
    void save_whenSuccess_Entity(){
        MessageDTO dto = new  MessageDTO();
        assertDoesNotThrow(()->service.save(dto));
    }
    @Test
    void update_whenMessageIdNotExist_NotResourceException(){
        UUID id = UUID.randomUUID();
        MessageDTO dto = MessageDTO.builder().uuid(id).build();
        when(repository.existsById(id)).thenReturn(false);
        assertThrows(NotResourceException.class, ()->service.update(dto));
    }
    @Test
    void update_whenSuccess_Entity(){
        UUID id = UUID.randomUUID();
        MessageDTO dto = MessageDTO.builder().uuid(id).build();
        when(repository.existsById(id)).thenReturn(true);
        assertDoesNotThrow(()->service.update(dto));
    }
    @Test
    void delete_whenIdNotExist_NotResourceException(){
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(false);
        assertThrows(NotResourceException.class, ()->service.delete(id));
    }
    @Test
    void delete_whenSuccess_success(){
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(true);
        assertDoesNotThrow(()->service.delete(id));
    }
    @Test
    void findByUuid_whenIdNotExist_OptionalEmpty(){
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertEquals(service.findByUuid(id), Optional.empty());
    }
    @Test
    void findByUuid_whenSuccess_OptionalEntity() {
        UUID id = UUID.randomUUID();
        Message result = Message.builder().uuid(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(result));
        assertEquals(service.findByUuid(id), Optional.of(result));
    }
//    @Test
//    void findBySearch_whenFail_PageEmpty(){
//        UUID id = UUID.randomUUID();
//        UUID chatId = UUID.randomUUID();
//        Date dateStart = new Date();
//        Date dateEnd = new Date();
//        Pageable pageable = null;
//        when(repository.findBySearch(dateStart,dateEnd,"",id,1L,chatId,pageable)).thenReturn(new PageImpl<>(List.of()));
//        assertEquals(service.findBySearch(dateStart,dateEnd,"",id,1L,chatId,pageable), pageable.);
//
//    }
//    @Test
//    void findBySearch_whenSuccess_PageMessage(){
//        UUID id = UUID.randomUUID();
//        UUID chatId = UUID.randomUUID();
//    }
    @Test
    void findByUserId_whenIdNotExist_nothing(){
        when(repository.findByUserId(any())).thenReturn(List.of());
        assertEquals(service.findByUserId(1L),List.of());

    }
    @Test
    void findByUserId_whenSuccess_ListEntity(){
        Message result = Message.builder().userId(1L).build();
        when(repository.findByUserId(any())).thenReturn(List.of(result));
        assertEquals(service.findByUserId(1L),List.of(result));
    }
    @Test
    void existById_whenIdNotExist_false(){
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(false);
        assertEquals(service.existById(id),false);
    }
    @Test
    void existById_whenSuccess_true(){
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(true);
        assertEquals(service.existById(id),true);
    }

}