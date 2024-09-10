package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.UserPrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.repository.UserPrivilegeRepository;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
class UserPrivilegeServiceImplTest {

    @Mock
    public UserPrivilegeRepository repository;
    @Mock
    public UserPrivilegeDTOConvertor convertor;
    @Mock
    public UserWebClient userClient;
    @InjectMocks
    public UserPrivilegeServiceImpl service;

    @Test
    @Tag("findById")
    void findById_whenUserPrivilegeIsExist_OptionalEntity(){
        UUID id = UUID.randomUUID();
        User_Privilege result = new User_Privilege(id,1L,null);
        when(repository.findById(id)).thenReturn(Optional.of(result));
        assertEquals(service.findById(id),Optional.of(result));
    }

    @Test
    @Tag("findById")
    void findById_whenNotExistById_OptionalEmpty(){
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertEquals(service.findById(id),Optional.empty());
    }

    @Test
    @Tag("save")
    void save_whenNotExistById_OptionalEntity() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = new UserPrivilegeDTO(id,1L,null);
        User_Privilege result = new User_Privilege(id,1L,null);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        Exception ex = assertThrows(NotResourceException.class,() ->service.save(dto));
        assertEquals(ex.getMessage(),"user not found");
    }

    @Test
    @Tag("save")
    void save_whenExistById_OptionalEntity() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = new UserPrivilegeDTO(id,1L,null);
        User_Privilege result = new User_Privilege(id,1L,null);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(userClient.userExist(dto.getUserId())).thenReturn(true);
        when(repository.findById(dto.getUuid())).thenReturn(Optional.of(result));
        when(repository.save(any())).thenReturn(result);

        assertEquals(service.save(dto),result);

    }

    @Test
    @Tag("update")
    void update_whenExistByIdAndUserId_OptionalEntity() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = new UserPrivilegeDTO(id,1L,null);
        User_Privilege result = new User_Privilege(id,1L,null);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(userClient.userExist(dto.getUserId())).thenReturn(true);
        when(repository.findById(dto.getUuid())).thenReturn(Optional.of(result));
        when(repository.save(any())).thenReturn(result);

        assertEquals(service.update(dto),result);

    }


    @Test
    @Tag("update")
    void update_whenNotExistByUserId_ExistResourceException() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = new UserPrivilegeDTO(id,1L,null);
        when(userClient.userExist(dto.getUserId())).thenReturn(false);
        Exception ex = assertThrows(NotResourceException.class,() ->service.update(dto));
        assertEquals(ex.getMessage(),"user not found");
    }

    @Test
    @Tag("update")
    void update_whenNotExistById_ExistResourceException() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = new UserPrivilegeDTO(id,1L,null);
        when(repository.existsById(any())).thenReturn(false);
        assertThrows(NotResourceException.class,() ->service.update(dto));
    }

    @Test
    @Tag("delete")
    void delete_whenExistById_Success() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(()->service.delete(id));
    }

    @Test
    @Tag("delete")
    void delete_whenNotExistById_NotResourceException() {
        UUID id = UUID.randomUUID();

        when(repository.existsById(any())).thenReturn(false);
        assertThrows(NotResourceException.class,()->service.delete(id));
    }

    @Test
    @Tag("findByUserId")
    void findByUserId_whenExistByUserId_OptionalEntity() {
        UUID id = UUID.randomUUID();
        User_Privilege result = new User_Privilege(id,1L,null);
        when(repository.findByUserId(any())).thenReturn(Optional.of(result));
        assertEquals(service.findByUserId(1L),Optional.of(result));
    }

    @Test
    @Tag("findByUserId")
    void findByUserId_whenNotExistByUserId_NotResourceException() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertEquals(service.findByUserId(1L),Optional.empty());
    }

}