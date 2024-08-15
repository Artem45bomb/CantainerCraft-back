package org.containercraft.servicefilemanager.service.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicefilemanager.dto.DownloadReqFileDTO;
import static org.containercraft.servicefilemanager.dto.Action.FileAction;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.containercraft.servicefilemanager.service.socket.thread.FileLoadThread;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler extends TextWebSocketHandler {
    private final StorageService storageFiles;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private  Set<WebSocketSession> sessions = new CopyOnWriteArraySet();
    private final Map<WebSocketSession,Thread> sessionLoad = new ConcurrentHashMap<>();

    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        session.getAttributes().put("compression-level", 6);
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        DownloadReqFileDTO reqLoadFile = objectMapper.readValue(message.getPayload(),DownloadReqFileDTO.class);

        if(sessionLoad.containsKey(session) && !reqLoadFile.getAction().equals(FileAction.STOP_LOAD)){
           Thread thread = sessionLoad.get(session);
           log.info("stop thread for session");
           if(thread != null) thread.interrupt();
        }

        if(!reqLoadFile.getAction().equals(FileAction.STOP_LOAD)){
            log.info("load file:{}",reqLoadFile.getFilename());
            Thread thread = new FileLoadThread(session,reqLoadFile.getFilename(), reqLoadFile.getStartByte(), storageFiles);
            thread.start();
            sessionLoad.put(session,thread);
        }
        else if(sessionLoad.containsKey(session)) {
            log.info("stop load filename:{}",reqLoadFile.getFilename());
            Thread thread = sessionLoad.get(session);
            if(thread != null) thread.interrupt();
        }
    }

    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        sessions.remove(session);
        if(sessionLoad.containsKey(session)){
            Thread thread = sessionLoad.get(session);
            if(thread != null) thread.interrupt();
        }
    }
}


