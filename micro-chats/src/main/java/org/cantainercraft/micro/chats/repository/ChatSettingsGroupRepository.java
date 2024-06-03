package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSettingsGroupRepository extends JpaRepository<Chat_Settings_Group, UUID> {

    boolean save(UUID uuid, Chat_Settings_Group chat_settings_group);

    List<Chat_Settings_Group> findByChatSettingsId(UUID id);
}
