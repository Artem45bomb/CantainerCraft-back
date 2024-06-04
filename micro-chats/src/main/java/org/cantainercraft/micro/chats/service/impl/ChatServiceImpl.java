package org.cantainercraft.micro.chats.service.impl;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.repository.ChatRepository;
import org.cantainercraft.micro.chats.service.ChatService;
import org.springframework.stereotype.Service;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatDTOConvertor chatDTOConvertor;

    public Chat save(ChatDTO chatDTO){
        Chat chat = chatDTOConvertor.convertChatDTOToChat(chatDTO);
        return chatRepository.save(chat);
    }

    public boolean update(ChatDTO chatUpdateDTO){
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


    public List<Chat> findBySearch(UUID uuid, String name, TypeChat typeChat, Date dateStart, Date dateEnd){
        return chatRepository.findBySearch(uuid,name,typeChat,dateStart,dateEnd);
    }

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
