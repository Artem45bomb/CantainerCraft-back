package org.cantainercraft.micro.users.service.impl;

import org.cantainercraft.micro.users.convertor.UserOnlineDTOConvertor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.project.entity.users.User_Online;
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
class UserOnlineServiceImplTest {
    @Mock
    public UserOnlineRepository repository;
    @Mock
    public UserOnlineDTOConvertor dtoConvertor;
    @InjectMocks
    private UserOnlineServiceImpl service;

    @Test
    void findById_whenExistById_returnOptionalEntity() {
        UUID id = UUID.randomUUID();
        User_Online result =new User_Online(id,User.builder().build(),false);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(result));

        Assertions.assertEquals(service.findById(id),Optional.of(result));
    }

    @Test
    void findById_whenNotExistById_returnOptionalEmpty() {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertEquals(service.findById(id),Optional.empty());
    }

    @Test
    void save_whenNotUserExist_returnUserOnline() {
        UserOnlineDTO dto = UserOnlineDTO.builder()
                .user(User.builder().id(1L).build())
                .build();

        User_Online result = User_Online.builder()
                .user(User.builder().id(1L).build())
                .build();

        Mockito.when(dtoConvertor.convertDTOToEntity(Mockito.any())).thenReturn(result);
        Mockito.when(repository.save(Mockito.any())).thenReturn(result);
        Mockito.when(repository.existsByUserId(Mockito.any())).thenReturn(false);

        Assertions.assertEquals(service.save(dto),result);
    }

    @Test
    void save_whenUserExist_returnExceptionExistResource() {
        UserOnlineDTO dto = UserOnlineDTO.builder()
                .user(User.builder().id(1L).build())
                .build();

        Mockito.when(repository.existsByUserId(Mockito.any())).thenReturn(true);

        Assertions.assertThrows(ExistResourceException.class,() -> service.save(dto));
    }

    @Test
    void update_whenExistById_returnUserOnline() {
        UserOnlineDTO dto = UserOnlineDTO.builder()
                .user(User.builder().id(1L).build())
                .build();
        User_Online result = User_Online.builder()
                .user(User.builder().id(1L).build())
                .build();

        Mockito.when(repository.existsById(Mockito.any())).thenReturn(true);
        Mockito.when(repository.save(Mockito.any())).thenReturn(result);

        Assertions.assertEquals(service.update(dto),result);
    }

    @Test
    void update_whenNotExistById_ExceptionNotResourceException() {
        UserOnlineDTO dto = UserOnlineDTO.builder()
                .user(User.builder().id(1L).build())
                .build();
        User_Online result = User_Online.builder()
                .user(User.builder().id(1L).build())
                .build();

        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);
        Mockito.when(repository.save(Mockito.any())).thenReturn(result);

        Assertions.assertThrows(NotResourceException.class,() ->service.update(dto));
    }

    @Test
    void deleteById_whenExistById_thenNotException() {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.existsById(Mockito.any())).thenReturn(true);

        Assertions.assertDoesNotThrow(()->service.deleteById(id));
    }

    @Test
    void deleteById_whenNotExistById_thenExceptionNOtResource() {
        UUID id = UUID.randomUUID();

        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);

        Assertions.assertThrows(NotResourceException.class,()->service.deleteById(id));
    }


}