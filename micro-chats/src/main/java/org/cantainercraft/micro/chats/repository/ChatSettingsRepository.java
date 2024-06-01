package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatSettingsRepository extends JpaRepository<Chat_Settings, UUID> {
    //boolean delete(UUID uuid);

    //boolean update(UUID uuid, Chat_Settings settings);

    boolean save(UUID uuid, Chat_Settings settings);

    //Optional<Chat_Settings> findByUuid(UUID uuid);

    List<Chat_Settings> findByChatId(UUID chat_Id);


}







