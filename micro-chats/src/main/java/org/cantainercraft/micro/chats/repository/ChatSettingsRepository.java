package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatSettingsRepository extends JpaRepository<Chat_Settings, UUID> {
    Optional<Chat_Settings> findByChatUuid(UUID chatId);

    boolean existsByChatUuid(UUID chatId);
}








