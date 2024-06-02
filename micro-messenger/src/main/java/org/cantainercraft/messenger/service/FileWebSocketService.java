package org.cantainercraft.messenger.service;

import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

public interface FileWebSocketService {
     UUID getSessionChatId(WebSocketSession session);
}
