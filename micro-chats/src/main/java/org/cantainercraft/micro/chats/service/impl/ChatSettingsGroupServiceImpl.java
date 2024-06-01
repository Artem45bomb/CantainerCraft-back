package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsGroupDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.dto.ChatSettingsGroupDTO;
import org.cantainercraft.micro.chats.dto.ChatSettingsGroupUpdateDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsGroupRepository;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatSettingsGroupServiceImpl {

    private final ChatSettingsGroupRepository chatSettingsGroupRepository;
    private final ChatSettingsGroupDTOConvertor chatSettingsGroupDTOConvertor;

    public boolean delete(UUID uuid) {
        return chatSettingsGroupRepository.findById(uuid).isPresent();
    }

    public boolean update(ChatSettingsGroupUpdateDTO chatSettingsGroupUpdateDTO) {
        Chat_Settings_Group chatSettingsGroup = chatSettingsGroupDTOConvertor.convertChatSettingsGroupDTOToChatSettingsGroup(chatSettingsGroupUpdateDTO);
        chatSettingsGroup.setUuid(chatSettingsGroupUpdateDTO.getUuid());
        chatSettingsGroupRepository.save(chatSettingsGroup);
        return true;
    }

    public Chat_Settings_Group save(ChatSettingsGroupDTO chatSettingsGroupDTO)
    {
        Chat_Settings_Group chatSettingsGroup = chatSettingsGroupDTOConvertor.convertChatSettingsGroupDTOToChatSettingsGroup(chatSettingsGroupDTO);
        return chatSettingsGroupRepository.save(chatSettingsGroup);
    }


    public Optional<Chat_Settings_Group> findByUUID(UUID id) {
        return chatSettingsGroupRepository.findById(id);
    }

    public List<Chat_Settings_Group> findByGroupId(UUID id) {
        return chatSettingsGroupRepository.findByChatSettingsGroupId(id);
    }

}
