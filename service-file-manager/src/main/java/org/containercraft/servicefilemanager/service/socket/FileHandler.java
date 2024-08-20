package org.containercraft.servicefilemanager.service.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicefilemanager.dto.DownloadReqFileDTO;
import static org.containercraft.servicefilemanager.dto.Action.FileAction;

import org.containercraft.servicefilemanager.dto.RespSocket;
import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.service.files.ContentService;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.containercraft.servicefilemanager.service.socket.thread.FileLoadThread;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileHandler extends TextWebSocketHandler {
    private final StorageService storageFiles;
    private final ContentService contentService;
    private final ObjectMapper mapper;
    private final Map<WebSocketSession, FileLoadThread> sessionLoad = new ConcurrentHashMap<>();
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        sessions.add(session);
        session.getAttributes().put("compression-level", 6);
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        DownloadReqFileDTO reqLoadFile = mapper.readValue(message.getPayload(),DownloadReqFileDTO.class);
        Optional<Content> content = contentService.findBySrc(reqLoadFile.getFilename());

        if(!reqLoadFile.getAction().equals(FileAction.STOP_LOAD) && (content.isEmpty() || content.get().isDelete())){
            String payload = mapper.writeValueAsString(new RespSocket(FileAction.NOT_FOUND,"FILE IS NOT EXIST", reqLoadFile.getFilename()));
            for(WebSocketSession elem : sessions){
                if(elem.isOpen()) elem.sendMessage(new TextMessage(payload));
            }
            return;
        }

        if(reqLoadFile.getAction().equals(FileAction.DELETE_FILE) && session.isOpen()){
            content.get().setDelete(true);
            contentService.update(content.get());
            String payload = mapper.writeValueAsString(new RespSocket(FileAction.DELETE_FILE,"FILE IS DELETE", reqLoadFile.getFilename()));
            session.sendMessage(new TextMessage(payload));
        }


        if(sessionLoad.containsKey(session)) {
            log.info("stop load filename:{}",reqLoadFile.getFilename());
            FileLoadThread thread = sessionLoad.get(session);
            sessionLoad.remove(session);
            if(thread != null) thread.disabled();
        }


        if(reqLoadFile.getAction().equals(FileAction.LOAD)){
            log.info("load file:{}",reqLoadFile.getFilename());
            FileLoadThread thread = new FileLoadThread(session,reqLoadFile.getFilename(), reqLoadFile.getStartByte(), storageFiles);
            thread.start();
            sessionLoad.put(session,thread);
        }

    }

    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        sessions.remove(session);
        if(sessionLoad.containsKey(session)){
            FileLoadThread thread = sessionLoad.get(session);
            sessionLoad.remove(session);
            if(thread != null) thread.disabled();
        }
    }
}


