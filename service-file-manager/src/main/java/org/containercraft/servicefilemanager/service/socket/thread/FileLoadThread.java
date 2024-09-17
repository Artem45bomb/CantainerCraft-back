package org.containercraft.servicefilemanager.service.socket.thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicefilemanager.dto.Action.FileAction;
import org.containercraft.servicefilemanager.dto.RespSocket;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
public class FileLoadThread extends Thread {
    private final StorageService service;
    private final WebSocketSession session;
    private int CHUNK_SIZE = 8 * 1024;
    private final String filename;
    private final Long startByte;
    private boolean isActive = true;
    public void disabled(){
        isActive = false;
    }


    public FileLoadThread(WebSocketSession session, String filename,Long startByte, Integer CHUNK_SIZE, StorageService service) {
        this.service = service;
        if (!session.isOpen()) throw new StorageException("session is close");
        if (CHUNK_SIZE > 0) this.CHUNK_SIZE = CHUNK_SIZE;
        this.session = session;
        this.filename = filename;
        this.startByte = startByte == null? 0 : startByte;
    }

    public FileLoadThread(WebSocketSession session, String filename,Long startByte, StorageService service) {
        this(session, filename, startByte,0, service);
    }

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        Path pathFile = service.validPath(filename);
        log.info("load file src:{}", pathFile);

        try (FileInputStream fis = new FileInputStream(pathFile.toFile())) {
            fis.skip(startByte);
            log.info("open load file src:{}", pathFile);
            int readLength;
            byte[] body = new byte[CHUNK_SIZE];


            while ((readLength = fis.read(body)) != -1 && isActive && session.isOpen()) {
                log.info("read file byte:{}", readLength);
                session.sendMessage(new BinaryMessage(body, 0, readLength, true));
            }

            if (!isActive) fis.close();
        } catch (FileNotFoundException ex)  {
                if(session.isOpen()) {
                    try {
                        String payload = mapper.writeValueAsString(new RespSocket(FileAction.DELETE_FILE,"FILE IS DELETE",pathFile.getFileName().toString()));
                        session.sendMessage(new TextMessage(payload));
                    } catch (IOException ignore){}
                }
        } catch (IOException ex) {
            log.error(ex.getMessage(),ex);
        }
    }
}
