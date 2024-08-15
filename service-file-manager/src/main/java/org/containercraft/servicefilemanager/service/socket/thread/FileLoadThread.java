package org.containercraft.servicefilemanager.service.socket.thread;

import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicefilemanager.dto.DownloadResFileDTO;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

@Slf4j
public class FileLoadThread extends Thread {
    private int CHUNK_SIZE = 8 * 1024;
    private final WebSocketSession session;
    private final String filename;
    private final StorageService service;
    private final Long startByte;


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
        Path pathFile = service.validPath(filename);
        log.info("load file src:{}", pathFile);

        try (FileInputStream fis = new FileInputStream(pathFile.toFile())) {
            fis.skip(startByte);
            log.info("open load file src:{}", pathFile);
            int readLength;
            byte[] body = new byte[CHUNK_SIZE];


            while ((readLength = fis.read(body)) != -1 && !Thread.currentThread().isInterrupted()) {
                log.info("read file byte:{}", readLength);

                if (session.isOpen())
                    session.sendMessage(new BinaryMessage(body, 0, readLength, true));
                else throw new RuntimeException("session is close");
            }

            if(Thread.currentThread().isInterrupted()) fis.close();
        } catch (FileNotFoundException ex) {
            throw new StorageException("file is not exist");
        } catch (Exception ex) {
            throw new StorageException(ex.getMessage(), ex);

        }
    }
}
