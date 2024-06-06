package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSettingsGroupRepository extends JpaRepository<Chat_Settings_Group, UUID> {

    List<Chat_Settings_Group> findByChatSettingsUuid(UUID uuid);
}
