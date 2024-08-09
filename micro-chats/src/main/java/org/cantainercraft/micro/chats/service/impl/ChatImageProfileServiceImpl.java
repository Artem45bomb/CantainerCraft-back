package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatImageProfileDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.service.ChatImageProfileService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
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

    @Override
    public Chat_Image_Profile save(ChatImageProfileDTO dto) {
        if(repository.existsBySrcContent(dto.getSrcContent())) throw new ExistResourceException("src is exist");

        Chat_Image_Profile entity = convertor.convertDTOToEntity(dto);

        return repository.save(entity);
    }

    @Override
    public Chat_Image_Profile update(ChatImageProfileDTO dto) {
        Chat_Image_Profile entity = convertor.convertDTOToEntity(dto);

        if (!repository.existsById(dto.getUuid())){
            throw new NotResourceException("No content to update");
        }

        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if (!repository.existsById(uuid)){
            throw new NotResourceException("No content to delete");
        }

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
    public List<Chat_Image_Profile> findByChatId(UUID uuid){
        return repository.findByChatUuid(uuid);
    }
}
