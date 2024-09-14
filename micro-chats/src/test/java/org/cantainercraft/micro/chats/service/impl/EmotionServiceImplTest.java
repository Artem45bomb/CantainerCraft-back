package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.EmotionDTOConvertor;
import org.cantainercraft.micro.chats.repository.EmotionRepository;
import org.cantainercraft.project.entity.chats.Emotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

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
    void findByUnicode() {

    }

    @Test
    void deleteByUnicode() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll_whenEntity_List() {

        when(repository.findAll()).thenReturn(Collections.singletonList(new Emotion()));

        Assertions.assertEquals(1, service.findAll().size());
    }

    @Test
    void findById() {
    }
}