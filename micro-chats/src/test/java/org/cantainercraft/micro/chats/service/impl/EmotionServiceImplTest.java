package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.EmotionDTOConvertor;
import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.micro.chats.repository.EmotionRepository;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class EmotionServiceImplTest{

    @Mock
    private EmotionRepository repository;

    @Mock
    private EmotionDTOConvertor convertor;

    @InjectMocks
    private EmotionServiceImpl service;


    @Test
    void findByUnicode_whenExist_returnEmotion() {
        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();
        String unicode = uuid.toString();

        when(repository.findByUnicode(unicode)).thenReturn(Optional.of(new Emotion()));

        assertEquals(emotion, service.findByUnicode(unicode).get());
    }

    @Test
    void findByUnicode_whenNotExist_notFind() {
        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();
        String unicode = uuid.toString();

        when(repository.findByUnicode(unicode)).thenReturn(Optional.empty());

        Optional<Emotion> result = service.findByUnicode(unicode);

        Assertions.assertFalse(result.isPresent(), "Expected no Emotion to be found");
    }

    @Test
    void deleteByUnicode_whenEntity_emotionDelete() {
        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();
        String unicode = uuid.toString();

        when(repository.findByUnicode(unicode)).thenReturn(Optional.of(emotion));

        service.deleteByUnicode(unicode);

        verify(repository).deleteByUnicode(unicode);
    }

    @Test
    void deleteByUnicode_whenEntity_emotionNotDelete() {
        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();
        String unicode = uuid.toString();
        when(repository.findByUnicode(unicode)).thenReturn(Optional.empty());
        assertThrows(NotResourceException.class, () -> service.deleteByUnicode(unicode));
    }

    @Test
    void deleteById_whenEntity_emotionDelete() {
        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenReturn(Optional.of(emotion));

        service.deleteById(uuid);

        verify(repository).deleteById(uuid);
    }

    @Test
    void deleteById_whenEntity_emotionNotDelete() {
        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotResourceException.class, () -> service.deleteById(uuid));
    }

    @Test
    void save_whenEntity_emotionIsExist() {

        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();
        EmotionDTO emotionDTO = new EmotionDTO("happy",uuid);

        when(repository.findByUnicode(new String("happy"))).thenReturn(Optional.of(emotion));

        ExistResourceException e =  assertThrows(ExistResourceException.class, () -> {
            service.save(emotionDTO);
        });

        assertEquals(e.getMessage(),"emotion is exist");

    }

    @Test
    void save_whenEntity_emotionIsNotExist() {
        Emotion emotion = new Emotion();
        Emotion result;
        UUID uuid = UUID.randomUUID();
        EmotionDTO emotionDTO = new EmotionDTO("happy",uuid);

        when(repository.findByUnicode(new String("qwe"))).thenReturn(Optional.empty());
        when(convertor.convertDTOToEntity(emotionDTO)).thenReturn(emotion);
        when(repository.save(emotion)).thenReturn(emotion);

        result = service.save(emotionDTO);

        assertEquals(emotion,result);
    }

    @Test
    void findAll_whenEntity_List() {

        when(repository.findAll()).thenReturn(singletonList(new Emotion()));

        assertEquals(1, service.findAll().size());
    }

    @Test
    void findById_whenEntityExists_returnOptionalEmotion() {

        Emotion emotion = new Emotion();
        UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenReturn(Optional.of(new Emotion()));

        assertEquals(emotion, service.findById(uuid).get());
    }
    @Test
    void findById_whenEntityDoesNotExist_returnEmptyOptional() {
        UUID uuid = UUID.randomUUID();

        when(repository.findById(uuid)).thenReturn(Optional.empty());

        Optional<Emotion> result = service.findById(uuid);

        Assertions.assertFalse(result.isPresent(), "Expected no Emotion to be found");
    }
}