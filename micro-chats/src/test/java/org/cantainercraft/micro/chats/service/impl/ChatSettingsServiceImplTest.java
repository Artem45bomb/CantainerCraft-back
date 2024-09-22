package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.ChatSettingsDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsRepository;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChatSettingsServiceImplTest {
    @Mock
    private ChatSettingsDTOConvertor convertor;

    @Mock
    private ChatSettingsRepository repository;

    @InjectMocks
    private ChatSettingsServiceImpl service;

    private UUID uuid;
    private ChatSettingsDTO chatSettingsDTO;
    private Chat_Settings chatSettingsEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        uuid = UUID.randomUUID();

        // Инициализация тестовых объектов
        chatSettingsDTO = new ChatSettingsDTO();
        chatSettingsDTO.setUuid(uuid);

        chatSettingsEntity = new Chat_Settings();
        chatSettingsEntity.setUuid(uuid);
    }

    @Test
    void save_shouldSaveAndReturnChatSettings() {
        when(convertor.convertDTOToEntity(chatSettingsDTO)).thenReturn(chatSettingsEntity);
        when(repository.save(chatSettingsEntity)).thenReturn(chatSettingsEntity);

        Chat_Settings result = service.save(chatSettingsDTO);

        assertNotNull(result);
        assertEquals(chatSettingsEntity, result);
        verify(repository, times(1)).save(chatSettingsEntity);
    }

    @Test
    void update_shouldUpdateAndReturnChatSettings() {
        when(repository.existsById(uuid)).thenReturn(true);
        when(convertor.convertDTOToEntity(chatSettingsDTO)).thenReturn(chatSettingsEntity);
        when(repository.save(chatSettingsEntity)).thenReturn(chatSettingsEntity);

        Chat_Settings result = service.update(chatSettingsDTO);

        assertNotNull(result);
        assertEquals(chatSettingsEntity, result);
        verify(repository, times(1)).save(chatSettingsEntity);
    }

    @Test
    void update_whenSettingsNotExist_throwNotResourceException() {
        when(repository.existsById(uuid)).thenReturn(false);

        NotResourceException exception = assertThrows(NotResourceException.class, () -> {
            service.update(chatSettingsDTO);
        });

        assertEquals("no settings by id", exception.getMessage());
        verify(repository, never()).save(any(Chat_Settings.class));
    }

    @Test
    void delete_whenSettingsExist_deleteChatSettings() {
        when(repository.existsById(uuid)).thenReturn(true);

        service.delete(uuid);

        verify(repository, times(1)).deleteById(uuid);
    }

    @Test
    void delete_whenSettingsNotExist_throwNotResourceException() {
        when(repository.existsById(uuid)).thenReturn(false);

        NotResourceException exception = assertThrows(NotResourceException.class, () -> {
            service.delete(uuid);
        });

        assertEquals("no settings by id", exception.getMessage());
        verify(repository, never()).deleteById(any(UUID.class));
    }

    @Test
    void findByUUID_shouldReturnOptionalChatSettings() {
        when(repository.findById(uuid)).thenReturn(Optional.of(chatSettingsEntity));

        Optional<Chat_Settings> result = service.findByUUID(uuid);

        assertTrue(result.isPresent());
        assertEquals(chatSettingsEntity, result.get());
        verify(repository, times(1)).findById(uuid);
    }

    @Test
    void findByUUID_whenNotExists_returnEmptyOptional() {
        when(repository.findById(uuid)).thenReturn(Optional.empty());

        Optional<Chat_Settings> result = service.findByUUID(uuid);

        assertFalse(result.isPresent());
        verify(repository, times(1)).findById(uuid);
    }

    @Test
    void findByChatId_shouldReturnListOfChatSettings() {
//        UUID chatId = UUID.randomUUID();
//        when(repository.findByChatUuid(chatId)).thenReturn(List.of(chatSettingsEntity));
//
//        List<Chat_Settings> result = service.findByChatId(chatId);
//
//        assertEquals(1, result.size());
//        assertEquals(chatSettingsEntity, result.get(0));
//        verify(repository, times(1)).findByChatUuid(chatId);
    }

    @Test
    void findAll_shouldReturnListOfChatSettings() {
        when(repository.findAll()).thenReturn(List.of(chatSettingsEntity));

        List<Chat_Settings> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals(chatSettingsEntity, result.get(0));
        verify(repository, times(1)).findAll();
    }

}