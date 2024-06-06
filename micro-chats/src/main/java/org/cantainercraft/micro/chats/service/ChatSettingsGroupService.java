package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.ChatSettingsGroupDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ChatSettingsGroupService {

    boolean delete(UUID uuid);

    boolean update(ChatSettingsGroupDTO chatSettingsGroupUpdateDTO);

    Chat_Settings_Group save(ChatSettingsGroupDTO chatSettingsGroupDTO);

    Optional<Chat_Settings_Group> findByUUID(UUID uuid);

    List<Chat_Settings_Group> findByGroupId(UUID uuid);
}
