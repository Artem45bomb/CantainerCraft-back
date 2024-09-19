package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.ChatImageProfileDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.repository.ChatImageProfileRepository;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ChatImageProfileServiceImplTest {

    @Mock
    private ChatImageProfileRepository repository;

    @Mock
    private ChatImageProfileDTOConvertor convertor;

    @InjectMocks
    private ChatImageProfileServiceImpl service;


    Chat_Image_Profile profile = new Chat_Image_Profile();
    ChatImageProfileDTO dto = new ChatImageProfileDTO();
    UUID uuid = UUID.randomUUID();

    @Test
    void save_shouldSave_Entity() {
        when(convertor.convertDTOToEntity(dto)).thenReturn(profile);
        when(repository.save(profile)).thenReturn(profile);

        Chat_Image_Profile result = service.save(dto);

        assertEquals(profile, result);
        verify(repository, times(1)).save(profile);
    }

    @Test
    void update_shouldUpdate_Entity() {
        when(convertor.convertDTOToEntity(dto)).thenReturn(profile);
        when(repository.existsById(uuid)).thenReturn(true);
        when(repository.save(profile)).thenReturn(profile);

        Chat_Image_Profile result = service.update(dto);

        assertEquals(profile, result);
        verify(repository).save(profile);
    }

    @Test
    void update_whenEntityDoesNotExist_throwNotResourceException() {
        when(convertor.convertDTOToEntity(dto)).thenReturn(profile);
        when(repository.existsById(uuid)).thenReturn(false);

        NotResourceException exception = assertThrows(NotResourceException.class, () -> {
            service.update(dto);
        });

        assertEquals("No content to update", exception.getMessage());
        verify(repository, never()).save(any(Chat_Image_Profile.class));
    }

    @Test
    void delete_whenEntity_DeleteEntity() {
        when(repository.existsById(uuid)).thenReturn(true);

        service.delete(uuid);

        verify(repository).deleteById(uuid);
    }

    @Test
    void delete_whenEntityDoesNotExist_throwNotResourceException() {
        when(repository.existsById(uuid)).thenReturn(false);

        NotResourceException exception = assertThrows(NotResourceException.class, () -> {
            service.delete(uuid);
        });

        assertEquals("No content to delete", exception.getMessage());
        verify(repository, never()).deleteById(any(UUID.class));
    }

    @Test
    void findAll_whenEntity_List() {
        when(repository.findAll()).thenReturn(singletonList(new Chat_Image_Profile()));

        assertEquals(1, service.findAll().size());
    }

    @Test
    void findById_whenEntityExists_Optional() {
        when(repository.findById(uuid)).thenReturn(Optional.of(new Chat_Image_Profile()));

        assertEquals(profile, service.findById(uuid).get());
    }

    @Test
    void findById_whenEntityDoesNotExist_EmptyOptional() {
        when(repository.findById(uuid)).thenReturn(Optional.empty());

        Optional<Chat_Image_Profile> result = service.findById(uuid);

        Assertions.assertFalse(result.isPresent(), "Expected no Chat_Image_Profile to be found");
    }

    @Test
    void findByChatId_List() {
        List<Chat_Image_Profile> expectedList = List.of(profile);
        when(repository.findByChatUuid(uuid)).thenReturn(expectedList);

        List<Chat_Image_Profile> result = service.findByChatId(uuid);

        assertEquals(expectedList, result);
        verify(repository).findByChatUuid(uuid);
    }
}