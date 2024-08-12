package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.repository.dto.ChatSettingsDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatSettingsService {

    void delete(UUID uuid);

    Chat_Settings update(ChatSettingsDTO chatSettingsDTO);

    Chat_Settings save(ChatSettingsDTO settings);

    Optional<Chat_Settings> findByUUID(UUID uuid);

    List<Chat_Settings> findByChatId(UUID chat_Id);

}
