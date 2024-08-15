package org.containercraft.servicefilemanager.service.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();
    Path load(String fileName);
    Stream<Path> loadAll();
    Resource loadAsResource(String filename);
    void store(MultipartFile file);
    void deleteAll();
    boolean delete(String fileName);

    void progressUnload(MultipartFile file,String fileId,Integer startByte);

    Path validPath(String filename);
}
