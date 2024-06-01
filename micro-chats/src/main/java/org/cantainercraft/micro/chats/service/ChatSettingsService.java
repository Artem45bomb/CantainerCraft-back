package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.dto.ChatSettingsUpdateDTO;
import org.cantainercraft.project.entity.chats.Chat_Settings;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatSettingsService {

    boolean delete(UUID uuid);

    boolean update(ChatSettingsUpdateDTO chatSettingsDTO);

    Chat_Settings save(ChatSettingsDTO settings);

    Optional<Chat_Settings> findByUUID(UUID uuid);

    List<Chat_Settings> findByChatId(UUID chat_Id);

}
