package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.repository.dto.ChatSettingsGroupDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ChatSettingsGroupService {

    void delete(UUID uuid);

    Chat_Settings_Group update(ChatSettingsGroupDTO chatSettingsGroupUpdateDTO);

    Chat_Settings_Group save(ChatSettingsGroupDTO chatSettingsGroupDTO);

    Optional<Chat_Settings_Group> findByUUID(UUID uuid);

    List<Chat_Settings_Group> findByGroupId(UUID uuid);
}
