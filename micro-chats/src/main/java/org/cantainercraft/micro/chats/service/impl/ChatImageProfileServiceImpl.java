package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatImageProfileDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.service.ChatImageProfileService;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.webflux.FileWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;
import org.cantainercraft.micro.chats.repository.ChatImageProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatImageProfileServiceImpl implements ChatImageProfileService {
    private final ChatImageProfileRepository repository;
    private final ChatImageProfileDTOConvertor convertor;
    private final ChatService chatService;
    private final FileWebClient fileClient;

    @Override
    public Chat_Image_Profile save(ChatImageProfileDTO dto) {
        Chat_Image_Profile entity = convertor.convertDTOToEntity(dto);
        Optional<Chat> chat = chatService.findByUUID(dto.getChat().getUuid());

        if(chat.isEmpty())
            throw new NotResourceException("chat is not exist");


        if(fileClient.findBySrc(dto.getSrcContent()) == null)
            throw new NotResourceException("src is not exist");

        return repository.save(entity);
    }

    @Override
    public Chat_Image_Profile update(ChatImageProfileDTO dto) {
        Chat_Image_Profile entity = convertor.convertDTOToEntity(dto);
        Optional<Chat> chat = chatService.findByUUID(dto.getChat().getUuid());

        if(chat.isEmpty())
            throw new NotResourceException("chat is not exist");

        if (!repository.existsById(dto.getUuid()))
            throw new NotResourceException("No content to update");

        if(fileClient.findBySrc(dto.getSrcContent()) == null)
            throw new NotResourceException("src is not exist");


        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if (!repository.existsById(uuid))
            throw new NotResourceException("No content to delete");


        repository.deleteById(uuid);
    }

    @Override
    public List<Chat_Image_Profile> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Chat_Image_Profile> findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public List<Chat_Image_Profile> findByChatId(UUID chatId){
        return repository.findByChatUuid(chatId);
    }
}
