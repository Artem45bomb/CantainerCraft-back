package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsGroupDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.ChatSettingsGroupDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsGroupRepository;
import org.cantainercraft.micro.chats.service.ChatSettingsGroupService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatSettingsGroupServiceImpl implements ChatSettingsGroupService {
    private final ChatSettingsGroupRepository repository;
    private final ChatSettingsGroupDTOConvertor convertor;


    public void delete(UUID uuid) {
        if(!repository.existsById(uuid))
        {
            throw new NotResourceException("no settingsGroup by id");
        }
        repository.deleteById(uuid);
    }

    public Chat_Settings_Group update(ChatSettingsGroupDTO chatSettingsGroupDTO) {
        if(!repository.existsById(chatSettingsGroupDTO.getUuid())) {
            throw new NotResourceException("no settingsGroup by id");
        }
        Chat_Settings_Group chatSettingsGroup = convertor.convertDTOToEntity(chatSettingsGroupDTO);
        return repository.save(chatSettingsGroup);
    }

    public Chat_Settings_Group save(ChatSettingsGroupDTO chatSettingsGroupDTO)
    {
        Chat_Settings_Group chatSettingsGroup = convertor.convertDTOToEntity(chatSettingsGroupDTO);
        return repository.save(chatSettingsGroup);
    }


    public Optional<Chat_Settings_Group> findByUUID(UUID uuid) {
        return repository.findById(uuid);
    }

    public List<Chat_Settings_Group> findByGroupId(UUID uuid) {
        return repository.findByChatSettingsUuid(uuid);
    }

}
