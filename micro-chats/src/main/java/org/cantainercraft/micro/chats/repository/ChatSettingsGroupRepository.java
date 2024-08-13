package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatSettingsGroupRepository extends JpaRepository<Chat_Settings_Group, UUID> {
<<<<<<< HEAD
=======
    List<Chat_Settings_Group> findByChatSettingsUuid(UUID uuid);
>>>>>>> 94cecfd0009b9c337586f2f3a2656c61a520ca06
}
