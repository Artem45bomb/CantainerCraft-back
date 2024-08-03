package org.cantainercraft.messenger.service;

import org.cantainercraft.messenger.dto.ChatSecuredDTO;

import java.util.List;
import java.util.UUID;

public interface ChatSecuredService {
    List<ChatSecuredDTO> findByUserIdChatSecured( Long userId);

    ChatSecuredDTO save(ChatSecuredDTO dto);

    void delete(ChatSecuredDTO dto);

    void deleteById(UUID uuid);
}
