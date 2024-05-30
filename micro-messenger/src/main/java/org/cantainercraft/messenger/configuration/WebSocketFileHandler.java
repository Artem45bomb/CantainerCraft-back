package org.cantainercraft.messenger.configuration;


import feign.Headers;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

@Component
@Log4j2
public class WebSocketFileHandler extends TextWebSocketHandler {

    private final String SESSION_KEY = "session-chat-id";
    private Map<UUID,Set<WebSocketSession>> sessionStore = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        HttpHeaders headers = session.getHandshakeHeaders();
        List<String> field =  headers.get(SESSION_KEY);

        log.info("field:{}",field.get(0));
        UUID sessionChatId =UUID.fromString(field.get(0));

        if(sessionStore.containsKey(sessionChatId)){
            sessionStore.get(sessionChatId).add(session);
        }
        else {
            Set<WebSocketSession> set = new HashSet<>();
            set.add(session);
            sessionStore.put(sessionChatId,set);
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

            //super.handleTextMessage(session,message);
            //session.close(CloseStatus.NOT_ACCEPTABLE.withReason("Text messages not supported"));

            HttpHeaders headers = session.getHandshakeHeaders();
            List<String> field =  headers.get(SESSION_KEY);

            log.info("field:{}",field.get(0));
            UUID sessionChatId =UUID.fromString(field.get(0));

            String payload = message.getPayload();

            if(session.isOpen()){

                sessionStore.get(sessionChatId).forEach(wsSession -> {
                            try {
                                wsSession.sendMessage(message);
                            } catch (IOException ex) {

                            }}
                );
            }
            else {
                log.warn("Error:Closed connection");
            }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session,status);

        HttpHeaders headers = session.getHandshakeHeaders();
        List<String> field =  headers.get(SESSION_KEY);

        log.info("field:{}",field.get(0));
        UUID sessionChatId =UUID.fromString(field.get(0));

        sessionStore.get(sessionChatId).remove(session);
    }
}
