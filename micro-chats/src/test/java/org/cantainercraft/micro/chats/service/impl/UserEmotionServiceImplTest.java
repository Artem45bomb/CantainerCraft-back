package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.UserEmotionDTOConvertor;
import org.cantainercraft.micro.chats.dto.EmotionAddDTO;
import org.cantainercraft.micro.chats.dto.EmotionDeleteDTO;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.chats.repository.UserEmotionRepository;
import org.cantainercraft.micro.chats.service.EmotionService;
import org.cantainercraft.micro.chats.service.MessageService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.cantainercraft.project.entity.chats.Message;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.cantainercraft.project.entity.users.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;


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
        User_Emotion result = new User_Emotion(id,null,null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(id,null,null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.save(any())).thenReturn(result);
        when(repository.existsById(Mockito.any())).thenReturn(true);
        Exception ex = assertThrows(Exception.class, ()->service.save(dto));
        assertEquals(ex.getClass(),NotValidateParamException.class);

    }

    @Test
    @Tag("save")
    void save_WhenUserIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        User_Emotion result = new User_Emotion(null,null,null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(null,null,null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.save(any())).thenReturn(result);
        when(repository.existsById(any())).thenReturn(false);
        when(webClient.userExist(dto.getUserId())).thenReturn(false);

        Exception ex = assertThrows(NotResourceException.class, ()->service.save(dto));
        assertEquals( ex.getClass(), NotResourceException.class);


    }

    @Test
    @Tag("save")
    void save_WhenSuccess_OptionalEntity(){
        UUID id = UUID.randomUUID();
        User_Emotion result = new User_Emotion(null,null,null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(null,null,null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.existsById(id)).thenReturn(false);
        when(repository.save(any())).thenReturn(result);
        when(webClient.userExist(1L)).thenReturn(true);
        assertEquals(service.save(dto), result);
    }

    @Test
    @Tag("update")
    void update_WhenMessageIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        User_Emotion result = new User_Emotion(id,Message.builder().uuid(messageId).build(), null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(messageService.existById(any())).thenReturn(true);
        Exception ex = assertThrows(Exception.class, ()->service.update(dto));
        assertEquals(NotResourceException.class, ex.getClass());

    }

    @Test
    @Tag("update")
    void update_WhenIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        User_Emotion result = new User_Emotion(id,Message.builder().uuid(messageId).build(), null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(messageService.existById(any())).thenReturn(false);
        when(repository.existsById(any())).thenReturn(true);
        Exception ex = assertThrows(Exception.class, ()->service.update(dto));
        assertEquals(NotResourceException.class, ex.getClass());
    }

    @Test
    @Tag("update")
    void update_WhenUserIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        User_Emotion result = new User_Emotion(id,Message.builder().uuid(messageId).build(), null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(messageService.existById(any())).thenReturn(false);
        when(repository.existsById(any())).thenReturn(false);
        when(webClient.userExist(1L)).thenReturn(true);
        Exception ex = assertThrows(Exception.class, ()->service.update(dto));
        assertEquals(NotResourceException.class, ex.getClass());
    }

    @Test
    @Tag("update")
    void update_WhenSuccess_OptionalEntity(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        User_Emotion result = new User_Emotion(null,null,null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(messageService.existById(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(result);
        when(repository.existsById(any())).thenReturn(true);
        when(webClient.userExist(1L)).thenReturn(true);
        assertEquals(service.update(dto), result);

    }

    @Test
    @Tag("delete")
    void delete_WhenIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        User_Emotion result = new User_Emotion(null,null,null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.existsById(any())).thenReturn(false);
        Exception ex = assertThrows(NotResourceException.class,() ->service.delete(dto.getUuid()));
        assertEquals(NotResourceException.class,ex.getClass());

    }

    @Test
    @Tag("delete")
    void delete_WhenSuccess_Success(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        User_Emotion result = new User_Emotion(null,null,null,1L);
        when(convertor.convertDTOToEntity(any())).thenReturn(result);
        when(repository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(()->service.delete(dto.getUuid()));
    }

    @Test
    @Tag("findById")
    void findById_WhenIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        when(repository.existsById(any())).thenReturn(true);
        assertThrows(NotResourceException.class, ()->service.findById(dto.getUuid()));
    }

    @Test
    @Tag("findById")
    void findById_WhenSuccess_OptionalEntity(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        User_Emotion result = new User_Emotion(id,Message.builder().uuid(messageId).build(), null,1L);
        UserEmotionDTO dto = new UserEmotionDTO(id,Message.builder().uuid(messageId).build(),null,1L);
        when(repository.findById(any())).thenReturn(Optional.of(result));
        assertDoesNotThrow(()->service.findById(dto.getUuid()));
    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenMessageNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UUID emotionId = UUID.randomUUID();
        EmotionAddDTO dto = new EmotionAddDTO(id,messageId,emotionId,1L);
        when(messageService.findByUuid(any())).thenReturn(Optional.empty());
        Exception ex = assertThrows(NotResourceException.class, ()->service.addEmotion(dto));
        assertEquals("message is not exist", ex.getMessage());

    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenEmotionNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UUID emotionId = UUID.randomUUID();
        EmotionAddDTO dto = new EmotionAddDTO(id,messageId,emotionId,1L);
        Message message = new Message();
        when(messageService.findByUuid(any())).thenReturn(Optional.of(message));
        when(emotionService.findById(any())).thenReturn(Optional.empty());
        Exception ex = assertThrows(NotResourceException.class, ()->service.addEmotion(dto));
        assertEquals("emotion is not exist", ex.getMessage());
    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenUserIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UUID emotionId = UUID.randomUUID();
        Message message = new Message();
        Emotion emotion = new Emotion();
        EmotionAddDTO dto = new EmotionAddDTO(id,messageId,emotionId,1L);
        when(messageService.findByUuid(any())).thenReturn(Optional.of(message));
        when(emotionService.findById(any())).thenReturn(Optional.of(emotion));
        when(webClient.userExist(1L)).thenReturn(false);

        Exception ex = assertThrows(NotResourceException.class, ()->service.addEmotion(dto));
        assertEquals("user is not exist", ex.getMessage());
    }

    @Test
    @Tag("addEmotion")
    void addEmotion_WhenSuccess_OptionalEntity(){
        UUID id = UUID.randomUUID();
        UUID messageId = UUID.randomUUID();
        UUID emotionId = UUID.randomUUID();
        Message message = new Message();
        Emotion emotion = new Emotion();
        EmotionAddDTO dto = new EmotionAddDTO(id,messageId,emotionId,1L);
        when(messageService.findByUuid(any())).thenReturn(Optional.of(message));
        when(emotionService.findById(any())).thenReturn(Optional.of(emotion));
        when(webClient.userExist(1L)).thenReturn(true);
        assertDoesNotThrow(()->service.addEmotion(dto));
    }

    @Test
    @Tag("deleteEmotion")
    void deleteEmotion_WhenIdNotExist_ThrowException(){
        UUID id = UUID.randomUUID();
        EmotionDeleteDTO dto = new EmotionDeleteDTO(id);
        when(repository.existsById(any())).thenReturn(true);
        assertThrows(NotResourceException.class, ()-> service.deleteEmotion(dto));

    }

    @Test
    @Tag("deleteEmotion")
    void deleteEmotion_WhenSuccess_Success(){
        UUID id = UUID.randomUUID();
        EmotionDeleteDTO dto = new EmotionDeleteDTO(id);
        User_Emotion result = new User_Emotion(null,null,null,1L);
        when(repository.findById(any())).thenReturn(Optional.of(result));
        assertDoesNotThrow(()->service.deleteEmotion(dto));
    }

}