package org.cantainercraft.micro.chats.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.repository.ChatRepository;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.springframework.stereotype.Service;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository repository;
    private final ConvertorDTO<ChatDTO,Chat> convertor;

    public Chat save(ChatDTO dto){
        Chat chat = convertor.convertDTOToEntity(dto);

        log.info("create chat ,link:{}",dto.getLink());
        if(repository.existsByLink(dto.getLink())) {
            throw new ExistResourceException("link for chat is exist");
        }
        
        return repository.save(chat);
    }

    public Chat update(ChatDTO dto){
        Chat chat = convertor.convertDTOToEntity(dto);

        if(repository.existsByLink(dto.getLink())){
            throw new ExistResourceException("link for chat is exist");
        }
        
        return repository.save(chat);
    }

     public void delete(UUID uuid){
        if(!repository.existsById(uuid)){
            throw new NotResourceException("chat is not exist");
        }

        repository.deleteById(uuid);
    }


    public List<Chat> findBySearch(UUID uuid, String name, TypeChat typeChat, Date dateStart, Date dateEnd){
        return repository.findBySearch(uuid,name,typeChat,dateStart,dateEnd);
    }

    public List<Chat> findAll(){
        return repository.findAll();
    }

    public Optional<Chat> findByUUID(UUID uuid){
        return repository.findById(uuid);
    }
}
