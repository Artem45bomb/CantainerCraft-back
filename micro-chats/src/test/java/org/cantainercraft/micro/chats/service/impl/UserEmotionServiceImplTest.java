package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.UserEmotionDTOConvertor;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.chats.repository.UserEmotionRepository;
import org.cantainercraft.micro.chats.service.EmotionService;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserEmotionServiceImplTest {

    @Mock
    public UserEmotionRepository repository;
    @Mock
    public UserEmotionDTOConvertor convertor;
    @Mock
    public MessageService messageService;
    @Mock
    public EmotionService emotionService;
    @Mock
    public UserWebClient webClient;
    @InjectMocks
    public UserEmotionServiceImpl service;

    @Test
    @Tag("save")
    void save_WhenIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UserEmotionDTO dto = UserEmotionDTO.builder()
                .uuid(id)
                .userId(1L)
                .build();
        User_Emotion result = User_Emotion.builder()
                .uuid(id)
                .userId(1L)
                .build();
        Mockito.when(convertor.convertDTOToEntity(Mockito.any())).thenReturn(result);
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);
        Exception ex = Assertions.assertThrows(Exception.class, ()->service.save(dto));

    }

    @Test
    @Tag("save")
    void save_WhenUserNotExist_ThrowException(){//     ?


    }

    @Test
    @Tag("save")
    void save_WhenSuccess_OptionalEntity(){

    }

    @Test
    @Tag("update")
    void update_WhenMessageIdNotExist_ThrowException(){

    }

    @Test
    @Tag("update")
    void update_WhenIdNotExist_ThrowException(){

    }

    @Test
    @Tag("update")
    void update_WhenUserIdNotExist_ThrowException(){

    }

    @Test
    @Tag("update")
    void update_WhenSuccess_OptionalEntity(){

    }

    @Test
    @Tag("delete")
    void delete_WhenIdNotExist_ThrowException(){

    }

    @Test
    @Tag("delete")
    void delete_WhenSuccess_(){

    }

    @Test
    @Tag("findById")
    void findById_WhenIdNotExist_ThrowException(){

    }

    @Test
    @Tag("findById")
    void findById_WhenSuccess_OptionalEntity(){

    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenMessageNotExist_ThrowException(){

    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenEmotionNotExist_ThrowException(){

    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenUserIdNotExist_ThrowException(){

    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenSuccess_OptionalEntity(){

    }

    @Test
    @Tag("deleteEmotion")
    void deleteEmotion_WhenIdNotExist_ThrowException(){

    }

    @Test
    @Tag("deleteEmotion")
    void deleteEmotion_WhenSuccess_Success(){

    }

}