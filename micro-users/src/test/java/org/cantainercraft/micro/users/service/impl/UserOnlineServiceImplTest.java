package org.cantainercraft.micro.users.service.impl;

import org.cantainercraft.micro.users.convertor.UserOnlineDTOConvertor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.project.entity.users.User_Online;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class UserOnlineServiceImplTest {

    @Mock
    public UserOnlineRepository repository;
    @Mock
    public UserOnlineDTOConvertor dtoConvertor;

    @InjectMocks
    private UserOnlineServiceImpl service;

    @Test
    void findAll() {

    }

    @Test
    void findById() {
    }

    @Test
    void save_whenUserExist_returnUserOnline() {
        UserOnlineDTO dto = UserOnlineDTO.builder()
                .user(User.builder().id(1L).build())
                .build();

        User_Online result = User_Online.builder()
                .user(User.builder().id(1L).build())
                .build();

        Mockito.when(dtoConvertor.convertDTOToEntity(Mockito.any())).thenReturn(result);
        Mockito.when(repository.save(Mockito.any())).thenReturn(result);
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);

        Assertions.assertEquals(service.save(dto),result);
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}