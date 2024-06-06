package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsGroupDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsGroupDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsGroupRepository;
import org.cantainercraft.micro.chats.service.ChatSettingsGroupService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatSettingsGroupServiceImpl implements ChatSettingsGroupService {

    private final ChatSettingsGroupRepository chatSettingsGroupRepository;
    private final ChatSettingsGroupDTOConvertor chatSettingsGroupDTOConvertor;

    public boolean delete(UUID uuid) {
        if(!chatSettingsGroupRepository.existsById(uuid))
        {
            throw new NotResourceException("NoN");
        }
        chatSettingsGroupRepository.deleteById(uuid);
        return true;
    }

    public boolean update(ChatSettingsGroupDTO chatSettingsGroupDTO) {
        if(!chatSettingsGroupRepository.existsById(chatSettingsGroupDTO.getUuid())) {
            throw new NotResourceException("NoN");
        }
        Chat_Settings_Group chatSettingsGroup = chatSettingsGroupDTOConvertor.convertChatSettingsGroupDTOToChatSettingsGroup(chatSettingsGroupDTO);
        chatSettingsGroupRepository.save(chatSettingsGroup);
        return true;
    }

    public Chat_Settings_Group save(ChatSettingsGroupDTO chatSettingsGroupDTO)
    {
        Chat_Settings_Group chatSettingsGroup = chatSettingsGroupDTOConvertor.convertChatSettingsGroupDTOToChatSettingsGroup(chatSettingsGroupDTO);
        return chatSettingsGroupRepository.save(chatSettingsGroup);
    }


    public Optional<Chat_Settings_Group> findByUUID(UUID uuid) {
        return chatSettingsGroupRepository.findById(uuid);
    }

    public List<Chat_Settings_Group> findByGroupId(UUID uuid) {
        return chatSettingsGroupRepository.findByChatSettingsUuid(uuid);
    }

}
