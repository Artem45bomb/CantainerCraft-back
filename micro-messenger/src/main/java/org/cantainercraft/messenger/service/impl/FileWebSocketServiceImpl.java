package org.cantainercraft.messenger.service.impl;

import org.cantainercraft.messenger.service.FileWebSocketService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;


import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.UUID;

@Service
public class FileWebSocketServiceImpl implements FileWebSocketService {
    private static final String SESSION_KEY = "SessionId";
    @Override
    public UUID getSessionChatId(WebSocketSession session) {
        HttpHeaders headers = session.getHandshakeHeaders();
        List<String> field =  headers.get(SESSION_KEY);


        UUID sessionChatId =UUID.fromString(field.get(0));

        System.out.println(sessionChatId);
        return sessionChatId;
    }
}
