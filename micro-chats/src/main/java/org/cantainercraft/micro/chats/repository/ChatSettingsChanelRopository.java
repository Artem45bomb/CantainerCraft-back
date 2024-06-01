package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSettingsChanelRopository extends JpaRepository<Chat_Settings_Chanel, UUID>{

    boolean save(UUID uuid, Chat_Settings_Chanel chat_settings_chanel);

    List<Chat_Settings_Chanel> findByChatId(UUID chat_id);
}
