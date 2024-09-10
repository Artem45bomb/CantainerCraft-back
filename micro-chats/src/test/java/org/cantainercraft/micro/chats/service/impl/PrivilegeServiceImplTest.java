package org.cantainercraft.micro.chats.service.impl;

import org.bouncycastle.LICENSE;
import org.cantainercraft.micro.chats.convertor.PrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.repository.PrivilegeRepository;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Privilege;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PrivilegeServiceImplTest {

    @Mock
    public PrivilegeRepository repository;
    @Mock
    public PrivilegeDTOConvertor convertor;
    @InjectMocks
    public PrivilegeServiceImpl service;

    @Test
    @Tag("save")
    void save_whenChatIdAndNameRoleNotExist_NotResourceException(){
        UUID id = UUID.randomUUID();
        PrivilegeDTO dto = PrivilegeDTO.builder().nameRole("role").chat(Chat.builder().uuid(id).build()).build();
        Privilege result = Privilege.builder().nameRole("role").chat(Chat.builder().uuid(id).build()).build();

        when(convertor.convertDTOToEntity(dto)).thenReturn(result);
        when(repository.existsByChatUuidAndNameRole(id,"role")).thenReturn(true);
        Exception ex = Assertions.assertThrows(NotResourceException.class,() ->service.save(dto));
        assertEquals(NotResourceException.class,ex.getClass());
    }
    @Test
    @Tag("save")
    void save_whenSuccess_Entity(){

    }
    @Test
    @Tag("update")
    void update_whenPrivilegeNotExist_NotResourceException(){
        UUID id = UUID.randomUUID();
        PrivilegeDTO dto = PrivilegeDTO.builder().chat(Chat.builder().uuid(id).build()).build();
        when(repository.findById(any())).thenReturn(Optional.empty());
        Exception ex = assertThrows(Exception.class, () -> service.update(dto));
        assertEquals(ex.getMessage(), "Privilege already exists");
    }
    @Test
    @Tag("update")
    void update_whenDTONameRoleNotEqualPrivilegeNameRole_NotResourceException(){
        UUID id = UUID.randomUUID();
        PrivilegeDTO dto = PrivilegeDTO.builder().nameRole("newRole").chat(Chat.builder().uuid(id).build()).build();
        Privilege result = Privilege.builder().nameRole("NewRole").chat(Chat.builder().uuid(id).build()).build();
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.save(any())).thenReturn(result);
        when(repository.findById(any())).thenReturn(Optional.of(result));
        when(repository.existsByChatUuidAndNameRole(dto.getChat().getUuid(), dto.getNameRole())).thenReturn(true);

        Exception ex = assertThrows(NotResourceException.class, () -> service.update(dto));
        assertEquals("This privilege already exists", ex.getMessage());
    }
    @Test
    @Tag("update")
    void update_whenSuccess_OptionalEntity(){
        UUID id = UUID.randomUUID();
        PrivilegeDTO dto = PrivilegeDTO.builder().nameRole("ddd").chat(Chat.builder().uuid(id).build()).build();
        Privilege result = Privilege.builder().nameRole("ddd").chat(Chat.builder().uuid(id).build()).build();
        when(repository.save(any())).thenReturn(result);
        when(repository.findById(any())).thenReturn(Optional.of(result));
        when(repository.existsByChatUuidAndNameRole(id,"")).thenReturn(true);
        assertEquals(service.update(dto), result);
    }
    @Test
    @Tag("deleteById")
    void deleteById_whenPrivilegeNotExist_NotResourceException(){
        UUID id = UUID.randomUUID();
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotResourceException.class,()->service.deleteById(id));
    }
    @Test
    @Tag("deleteById")
    void deleteById_whenSuccess_Success(){
        UUID id = UUID.randomUUID();
        Privilege result = Privilege.builder().nameRole("ddd").chat(Chat.builder().uuid(id).build()).build();
        when(repository.findById(any())).thenReturn(Optional.of(result));
        assertDoesNotThrow(()->service.deleteById(id));

    }
    @Test
    @Tag("findById")
    void findById_whenIdNotExist_Nothing(){
        UUID id = UUID.randomUUID();
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertEquals(service.findById(id),Optional.empty());
    }
    @Test
    @Tag("findById")
    void findById_whenSuccess_OptionalEntity(){
        UUID id = UUID.randomUUID();
        Privilege result = Privilege.builder().nameRole("ddd").chat(Chat.builder().uuid(id).build()).build();
        when(repository.findById(any())).thenReturn(Optional.of(result));
        assertEquals(service.findById(id),Optional.of(result));
    }
    @Test
    @Tag("findBySearch")
    void findByChat_whenChatIdNotExist_Nothing(){
        UUID id = UUID.randomUUID();
        when(repository.findByChatUuid(id)).thenReturn(List.of());
        assertEquals(service.findByChat(id), List.of());

    }

    @Test
    @Tag("findBySearch")
    void findByChat_Success_ListEntity(){
        UUID id = UUID.randomUUID();
        Privilege result = Privilege.builder().chat(Chat.builder().uuid(id).build()).build();
        when(repository.save(any())).thenReturn(List.of(result));
        when(repository.findByChatUuid(any())).thenReturn(List.of(result));
        assertEquals(service.findByChat(id),List.of(result));
    }
}