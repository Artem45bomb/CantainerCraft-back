package org.containercraft.servicerealtime.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


public class DownloadFileHandler extends TextWebSocketHandler {
    private Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception{
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {

    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,@NonNull CloseStatus closeStatus) throws Exception{
        sessions.remove(session);
    }

}
