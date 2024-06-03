package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSettingsChanelRepository extends JpaRepository<Chat_Settings_Chanel, UUID>{


}
