package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.repository.dto.ChatSettingsChanelDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;

import java.util.Optional;
import java.util.UUID;

public interface ChatSettingsChanelService {

    void delete(UUID uuid);

    Chat_Settings_Chanel update(ChatSettingsChanelDTO chatSettingsChanelDTO);

    Chat_Settings_Chanel save(ChatSettingsChanelDTO settings);

    Optional<Chat_Settings_Chanel> findByUUID(UUID uuid);
}
