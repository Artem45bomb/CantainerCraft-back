package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.UserPrivilegeDTOConvertor;
import org.cantainercraft.micro.chats.repository.UserPrivilegeRepository;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.project.entity.chats.Privilege;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        User_Privilege result = new User_Privilege(id, User.builder().build().getId(), Privilege.builder().build());
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(result));
        Assertions.assertEquals(service.findById(id),Optional.of(result));
    }

    @Test
    @Tag("findById")
    void findById_whenNotExistById_OptionalEmpty(){
        UUID id = UUID.randomUUID();
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertEquals(service.findById(id),Optional.empty());
    }

    @Test
    @Tag("save")
    void save_whenNotExistById_OptionalEntity() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = UserPrivilegeDTO.builder()
                .userId(1L)
                .uuid(id)
                .build();
        User_Privilege result = User_Privilege.builder()
                .userId(1L)
                .uuid(id)
                .build();

        Mockito.when(convertor.convertDTOToEntity(Mockito.any())).thenReturn(result);

       Exception ex = Assertions.assertThrows(NotResourceException.class,() ->service.save(dto));
       Assertions.assertEquals(ex.getMessage(),"user not found");
    }

    @Test
    @Tag("save")
    void save_whenExistById_OptionalEntity() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = UserPrivilegeDTO.builder()
                .userId(1L)
                .uuid(id)
                .build();
        User_Privilege result = User_Privilege.builder()
                .userId(1L)
                .uuid(id)
                .build();

        Mockito.when(convertor.convertDTOToEntity(Mockito.any())).thenReturn(result);
        Mockito.when(userClient.userExist(dto.getUserId())).thenReturn(true);
        Mockito.when(repository.findById(dto.getUuid())).thenReturn(Optional.of(result));
        Mockito.when(repository.save(Mockito.any())).thenReturn(result);

        Assertions.assertEquals(service.save(dto),result);

    }

    @Test
    @Tag("update")
    void update_whenExistByIdAndUserId_OptionalEntity() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = UserPrivilegeDTO.builder()
                .userId(1L)
                .uuid(id)
                .build();
        User_Privilege result = User_Privilege.builder()
                .userId(1L)
                .uuid(id)
                .build();
        Mockito.when(convertor.convertDTOToEntity(Mockito.any())).thenReturn(result);
        Mockito.when(userClient.userExist(dto.getUserId())).thenReturn(true);
        Mockito.when(repository.findById(dto.getUuid())).thenReturn(Optional.of(result));
        Mockito.when(repository.save(Mockito.any())).thenReturn(result);

        Assertions.assertEquals(service.update(dto),result);

    }


    @Test
    @Tag("update")
    void update_whenNotExistByUserId_ExistResourceException() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = UserPrivilegeDTO.builder()
                .userId(1L)
                .uuid(id)
                .build();
        Mockito.when(userClient.userExist(dto.getUserId())).thenReturn(false);
        Exception ex = Assertions.assertThrows(NotResourceException.class,() ->service.update(dto));
        Assertions.assertEquals(ex.getMessage(),"user not found");
    }

    @Test
    @Tag("update")
    void update_whenNotExistById_ExistResourceException() {
        UUID id = UUID.randomUUID();
        UserPrivilegeDTO dto = UserPrivilegeDTO.builder()
                .userId(1L)
                .uuid(id)
                .build();
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);
        Assertions.assertThrows(NotResourceException.class,() ->service.update(dto));
    }

    @Test
    @Tag("delete")
    void delete_whenExistById_Success() {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.existsById(Mockito.any())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->service.delete(id));
    }

    @Test
    @Tag("delete")
    void delete_whenNotExistById_NotResourceException() {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);
        Assertions.assertThrows(NotResourceException.class,()->service.delete(id));
    }

    @Test
    @Tag("findByUserId")
    void findByUserId_whenExistByUserId_OptionalEntity() {
        Long userId = 1L;
        UUID id = UUID.randomUUID();
        User_Privilege result = User_Privilege.builder()
                        .uuid(id)
                        .userId(1L)
                        .build();
        Mockito.when(repository.findByUserId(Mockito.any())).thenReturn(Optional.of(result));
        Assertions.assertEquals(service.findByUserId(userId),Optional.of(result));
    }

    @Test
    @Tag("findByUserId")
    void findByUserId_whenNotExistByUserId_NotResourceException() {
        Long userId = 1L;
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertEquals(service.findByUserId(userId),Optional.empty());
    }

}