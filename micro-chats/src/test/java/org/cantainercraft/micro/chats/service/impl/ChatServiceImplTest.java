package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.repository.ChatRepository;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.Chat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ChatServiceImplTest {

    @Mock
    private ChatRepository repository;

    @Mock
    private ConvertorDTO<ChatDTO, Chat> convertor;

    @InjectMocks
    private ChatServiceImpl service;

    private UUID uuid;
    private ChatDTO chatDTO;
    private Chat chatEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        uuid = UUID.randomUUID();

        chatDTO = new ChatDTO();
        chatDTO.setLink("http://text.com/chat");

        chatEntity = new Chat();
        chatEntity.setLink("http://text.com/chat");
    }

    @Test
    void save_shouldSaveAndReturnChat() {
        when(convertor.convertDTOToEntity(chatDTO)).thenReturn(chatEntity);
        when(repository.existsByLink(chatDTO.getLink())).thenReturn(false);
        when(repository.save(chatEntity)).thenReturn(chatEntity);

        Chat result = service.save(chatDTO);

        assertNotNull(result);
        assertEquals(chatEntity, result);
        verify(repository).save(chatEntity);
    }

    @Test
    void save_whenLinkExists_throwExistResourceException() {
        when(convertor.convertDTOToEntity(chatDTO)).thenReturn(chatEntity);
        when(repository.existsByLink(chatDTO.getLink())).thenReturn(true);

        ExistResourceException exception = assertThrows(ExistResourceException.class, () -> {
            service.save(chatDTO);
        });

        assertEquals("link for chat is exist", exception.getMessage());
        verify(repository, never()).save(any(Chat.class));
    }

    @Test
    void update_shouldUpdateAndReturnChat() {
        when(convertor.convertDTOToEntity(chatDTO)).thenReturn(chatEntity);
        when(repository.existsByLink(chatDTO.getLink())).thenReturn(false);
        when(repository.save(chatEntity)).thenReturn(chatEntity);

        Chat result = service.update(chatDTO);

        assertNotNull(result);
        assertEquals(chatEntity, result);
        verify(repository).save(chatEntity);
    }

    @Test
    void update_whenLinkExists_throwExistResourceException() {
        when(convertor.convertDTOToEntity(chatDTO)).thenReturn(chatEntity);
        when(repository.existsByLink(chatDTO.getLink())).thenReturn(true);

        ExistResourceException exception = assertThrows(ExistResourceException.class, () -> {
            service.update(chatDTO);
        });

        assertEquals("link for chat is exist", exception.getMessage());
        verify(repository, never()).save(any(Chat.class));
    }

    @Test
    void delete_whenExists_deleteChat() {
        when(repository.existsById(uuid)).thenReturn(true);

        service.delete(uuid);

        verify(repository, times(1)).deleteById(uuid);
    }

    @Test
    void delete_whenNotExists_throwNotResourceException() {
        when(repository.existsById(uuid)).thenReturn(false);

        NotResourceException exception = assertThrows(NotResourceException.class, () -> {
            service.delete(uuid);
        });

        assertEquals("chat is not exist", exception.getMessage());
        verify(repository, never()).deleteById(any(UUID.class));
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(chatEntity));

        List<Chat> result = service.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void findByUUID_shouldReturnOptionalChat() {
        when(repository.findById(uuid)).thenReturn(Optional.of(chatEntity));

        Optional<Chat> result = service.findByUUID(uuid);

        assertTrue(result.isPresent());
        assertEquals(chatEntity, result.get());
        verify(repository).findById(uuid);
    }

    @Test
    void findByUUID_whenNotExists_EmptyOptional() {
        when(repository.findById(uuid)).thenReturn(Optional.empty());

        Optional<Chat> result = service.findByUUID(uuid);

        assertFalse(result.isPresent());
        verify(repository).findById(uuid);
    }
}