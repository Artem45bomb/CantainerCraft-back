package ru.micro.chats.data.service;

import org.springframework.stereotype.Service;
import ru.micro.chats.data.repository.ChatRepository;
import ru.micro.chats.data.dto.ChatDTO;
import ru.micro.chats.data.dto.ChatDTOConvertor;
import ru.micro.chats.data.dto.ChatUpdateDTO;
import ru.weather.project.entity.chats.Chat;

import java.util.List;
import java.util.Optional;
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

    public boolean deleteByName(String name){
       return chatRepository.deleteByName(name);
    }


//    public List<Chat> findBySearch(UUID uuid, String name, TypeChat typeChat,Date dateStart,Date dateEnd){
//        return chatRepository.findBySearch(uuid,name,typeChat,dateStart,dateEnd);
//    }

    public Optional<Chat> findByName(String chatName){
        return chatRepository.findByName(chatName);
    }

    public List<Chat> findAll(){
        return chatRepository.findAll();
    }

    public Optional<Chat> findByUUID(UUID uuid){
        return chatRepository.findById(uuid);
    }
}
