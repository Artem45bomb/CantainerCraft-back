package org.cantainercraft.micro.chats.service.impl;

import org.cantainercraft.micro.chats.convertor.ChatImageProfileDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.micro.chats.repository.ChatImageProfileRepository;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.webflux.FileWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
class ChatImageProfileServiceImplTest {
    @Mock
    private  ChatImageProfileRepository repository;
    @Mock
    private  ChatImageProfileDTOConvertor convertor;
    @Mock
    private  ChatService chatService;
    @Mock
    private  FileWebClient fileClient;

    @InjectMocks
    private ChatImageProfileServiceImpl service;

    @Test
    void save_whenSuccess_returnEntity() {
        ChatImageProfileDTO dto = ChatImageProfileDTO.builder()
                .srcContent("/Arg.svg")
                .chat(new Chat())
                .build();
        Chat_Image_Profile result = new Chat_Image_Profile(UUID.randomUUID(),"/Arg.svg",new Chat());

        when(chatService.findByUUID(any())).thenReturn(Optional.of(new Chat()));
        when(fileClient.findBySrc(any())).thenReturn(new ContentDTO());
        when(repository.save(any())).thenReturn(result);

        Assertions.assertEquals(service.save(dto),result);
    }

    @Test
    void save_whenSrcIsNull_returnNotResourceException() {
        ChatImageProfileDTO dto = ChatImageProfileDTO.builder()
                .srcContent("/Arg.svg")
                .chat(new Chat())
                .build();

        when(chatService.findByUUID(any())).thenReturn(Optional.of(new Chat()));
        when(fileClient.findBySrc(any())).thenReturn(null);

        NotResourceException exception = Assertions.assertThrows(NotResourceException.class,() ->service.save(dto));
        Assertions.assertEquals(exception.getMessage(),"src is not exist");
    }

    @Test
    void save_whenChatIsNotExist_returnNotResourceException() {
        ChatImageProfileDTO dto = ChatImageProfileDTO.builder()
                .srcContent("/Arg.svg")
                .chat(new Chat())
                .build();
        when(chatService.findByUUID(any())).thenReturn(Optional.empty());


        NotResourceException exception = Assertions.assertThrows(NotResourceException.class,() ->service.save(dto));
        Assertions.assertEquals(exception.getMessage(),"chat is not exist");
    }

    @Test
    void update_whenSuccess_returnEntity() {
        ChatImageProfileDTO dto = ChatImageProfileDTO.builder()
                .srcContent("/Arg.svg")
                .chat(new Chat())
                .build();
        Chat_Image_Profile result = new Chat_Image_Profile(UUID.randomUUID(),"/Arg.svg",new Chat());

        when(repository.existsById(any())).thenReturn(true);
        when(fileClient.findBySrc(any())).thenReturn(new ContentDTO());
        when(repository.save(any())).thenReturn(result);

        Assertions.assertEquals(service.update(dto),result);
    }

    @Test
    void update_whenSrcIsNotExist_returnNotResourceException() {
        ChatImageProfileDTO dto = ChatImageProfileDTO.builder()
                .srcContent("/Arg.svg")
                .chat(new Chat())
                .build();
        Chat_Image_Profile result = new Chat_Image_Profile(UUID.randomUUID(),"/Arg.svg",new Chat());

        when(repository.existsById(any())).thenReturn(true);
        when(fileClient.findBySrc(any())).thenReturn(null);
        when(repository.save(any())).thenReturn(result);

        NotResourceException ex =  Assertions.assertThrows(NotResourceException.class,() -> service.update(dto));
        Assertions.assertEquals(ex.getMessage(),"src is not exist");
    }

    @Test
    void update_whenNotExist_returnNotResourceException() {
        ChatImageProfileDTO dto = ChatImageProfileDTO.builder()
                .srcContent("/Arg.svg")
                .chat(new Chat())
                .build();

        when(repository.existsById(any())).thenReturn(false);

        NotResourceException ex =  Assertions.assertThrows(NotResourceException.class,() -> service.update(dto));
        Assertions.assertEquals(ex.getMessage(),"No content to update");
    }

    @Test
    void delete_whenIsExist() {
        UUID id = UUID.randomUUID();

        when(repository.existsById(any())).thenReturn(true);
        doNothing().when(repository).deleteById(any());

        Assertions.assertDoesNotThrow(() ->service.delete(id));
    }

    @Test
    void delete_whenIsNotExist() {
        UUID id = UUID.randomUUID();

        when(repository.existsById(any())).thenReturn(false);
        doNothing().when(repository).deleteById(any());

        Exception exception = Assertions.assertThrows(NotResourceException.class,() ->service.delete(id));
        Assertions.assertEquals("No content to delete",exception.getMessage());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById_whenIsExist_Entity() {
        UUID id = UUID.randomUUID();
        Optional<Chat_Image_Profile> result = Optional.of(Chat_Image_Profile.builder()
                .uuid(id)
                .build());

        when(repository.findById(any())).thenReturn(result);

        Assertions.assertEquals(service.findById(id),result);
    }

    @Test
    void findById_whenIsNotExist_returnEmpty() {
        UUID id = UUID.randomUUID();

        when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertEquals(service.findById(id),Optional.empty());
    }

    @Test
    void findByChatId_whenIsNotExist_returnList() {
        UUID chatId = UUID.randomUUID();

        when(repository.findByChatUuid(chatId)).thenReturn(Collections.emptyList());

        Assertions.assertEquals(service.findByChatId(chatId),Collections.emptyList());
    }

    @Test
    void findByChatId_whenExist_returnList() {
        UUID chatId = UUID.randomUUID();

        List<Chat_Image_Profile> list = Collections.singletonList(new Chat_Image_Profile());
        when(repository.findByChatUuid(chatId)).thenReturn(list);

        Assertions.assertEquals(service.findByChatId(chatId),list);
    }

    @Test
    void findByAll_whenIsNotExist_returnList() {
        UUID chatId = UUID.randomUUID();

        when(repository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertEquals(service.findAll(),Collections.emptyList());
    }

    @Test
    void findByAll_whenExist_returnList() {
        UUID chatId = UUID.randomUUID();

        List<Chat_Image_Profile> list = Collections.singletonList(new Chat_Image_Profile());
        when(repository.findAll()).thenReturn(list);

        Assertions.assertEquals(service.findAll(),list);
    }
}


