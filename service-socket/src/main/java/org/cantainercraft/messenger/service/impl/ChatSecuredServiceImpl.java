package org.cantainercraft.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.ChatSecuredDTO;
import org.cantainercraft.messenger.service.ChatSecuredService;
import org.cantainercraft.messenger.webclient.ChatSecuredClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatSecuredServiceImpl implements ChatSecuredService {
    private final ChatSecuredClient client;

    @Override
    public List<ChatSecuredDTO> findByUserIdChatSecured(Long userId) {
        return client.findByUserIdChatSecured(userId);
    }

    @Override
    public ChatSecuredDTO save(ChatSecuredDTO dto) {
        return client.save(dto);
    }

    @Override
    public void delete(ChatSecuredDTO dto) {
        client.delete(dto);
    }

    @Override
    public void deleteById(UUID uuid) {
        client.deleteById(uuid);
    }
}
