package org.cantainercraft.messenger.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.cantainercraft.messenger.dto.WSFileDTO;
import org.cantainercraft.messenger.service.FileWebSocketService;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
@RequiredArgsConstructor
public class WebSocketFileHandler extends BinaryWebSocketHandler {
    private static final Integer SIZE_CHUNK = 1_024;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Map<UUID,Set<WebSocketSession>> sessionStore = new ConcurrentHashMap<>();
    private final FileWebSocketService service;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,TextMessage textMessage){
        File file = null;
        try {
            var dto = objectMapper.readValue(textMessage.getPayload(), WSFileDTO.class);
            file = new File(dto.getSrcContent());
        }
        catch (IOException ex){
            log.warn("Error:{}",ex.getMessage());
        }

        long fileLength = file.length();
        long fileReadLength = 0;

        try(var fileInput = new FileInputStream(file)){

            byte[] readArray = new byte[SIZE_CHUNK * 6];
            long realLength;

            while ((realLength = fileInput.read(readArray)) != -1){
               fileReadLength += realLength;
               boolean isLastMessage = fileLength  == fileReadLength;
               session.sendMessage(new BinaryMessage(readArray,isLastMessage));
            }
        }
        catch (IOException ex){
            log.warn("Error:{}",ex.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session,status);
    }
}
