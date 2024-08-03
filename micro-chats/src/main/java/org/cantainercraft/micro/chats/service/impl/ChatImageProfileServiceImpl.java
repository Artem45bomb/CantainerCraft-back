package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatImageProfileDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.service.ChatImageProfileService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
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
    public Chat_Image_Profile save(ChatImageProfileDTO chatImageProfileDTO) {
        if(!repository.existsBySrcContent(chatImageProfileDTO.getSrcContent())) throw new ExistResourceException("src is exist");

        Chat_Image_Profile entity = convertor.convertChatImageProfileDTOToChatImageProfile(chatImageProfileDTO);

        return repository.save(entity);
    }

    @Override
    public boolean update(ChatImageProfileDTO chatImageProfileDTO) {
        if (repository.existsById(chatImageProfileDTO.getUuid())) {
            Chat_Image_Profile entity = convertor.convertChatImageProfileDTOToChatImageProfile(chatImageProfileDTO);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID uuid) {
        if (repository.existsById(uuid)) {
            repository.deleteById(uuid);
            return true;
        }
        return false;
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
    public boolean existById(UUID uuid) {
        return repository.existsById(uuid);
    }
}
