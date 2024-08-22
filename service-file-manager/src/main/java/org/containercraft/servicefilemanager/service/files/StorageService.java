package org.containercraft.servicefilemanager.service.files;

import org.containercraft.servicefilemanager.entity.Content;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();
    Path load(String fileName);
    Stream<Path> loadAll();
    Resource loadAsResource(String filename);

    InputStreamResource loadAsRange(File file, long start, long end);
    Content store(MultipartFile file);
    void deleteAll();
    boolean delete(String fileName);

    void progressUnload(MultipartFile file,String fileId,int startByte);

    Path validPath(String filename);
}
