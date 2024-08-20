package org.containercraft.servicefilemanager.service.files;

import org.containercraft.servicefilemanager.entity.Content;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();
    Path load(String fileName);
    Stream<Path> loadAll();
    Resource loadAsResource(String filename);
    Content store(MultipartFile file);
    void deleteAll();
    boolean delete(String fileName);

    void progressUnload(MultipartFile file,String fileId,int startByte);

    Path validPath(String filename);
}
