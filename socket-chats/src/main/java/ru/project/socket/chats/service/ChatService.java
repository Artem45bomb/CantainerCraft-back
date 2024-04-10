package ru.project.socket.chats.service;

import org.springframework.stereotype.Service;
import ru.project.socket.chats.convertor.ChatDTOConvertor;
import ru.project.socket.chats.dto.ChatDTO;
import ru.project.socket.chats.dto.ChatUpdateDTO;
import ru.project.socket.chats.repository.ChatRepository;
import ru.weather.project.entity.Chat;

import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatDTOConvertor chatDTOConvertor;
    public ChatService(ChatRepository chatRepository,ChatDTOConvertor chatDTOConvertor){
        this.chatRepository = chatRepository;
        this.chatDTOConvertor = chatDTOConvertor;
    }

    public Chat save(ChatDTO chatDTO){
        Chat chat = chatDTOConvertor.convertChatDTOToChat(chatDTO);
        return chatRepository.save(chat);
    }

    public boolean update(ChatUpdateDTO chatUpdateDTO){
        Chat chat = chatDTOConvertor.convertChatDTOToChat(chatUpdateDTO);
        chat.setUuid(chatUpdateDTO.getUuid());
        chatRepository.save(chat);
        return true;
    }

    public boolean delete(UUID uuid){
        chatRepository.deleteById(uuid);
        return true;
    }

    public List<Chat> findAll(){
        return chatRepository.findAll();
    }

    public Chat findByUUID(UUID uuid){
        return chatRepository.findById(uuid).get();
    }
}
