package org.cantainercraft.micro.chats.service;


import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.project.entity.chats.Chat_Secured;

import java.util.List;
import java.util.UUID;

public interface ChatSecuredService {
    Chat_Secured save(ChatSecuredDTO dto);

    void deleteById(UUID uuid);

    void delete(ChatSecuredDTO chatSecuredDTO);

    List<ChatSecuredDTO> findAll();

    List<ChatSecuredDTO> findByUserId(Long userId);

}
