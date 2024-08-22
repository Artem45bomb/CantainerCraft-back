package org.containercraft.servicefilemanager.service.files.impl;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.containercraft.servicefilemanager.config.StorageProperties;
import org.containercraft.servicefilemanager.dto.ContentDTO;
import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.containercraft.servicefilemanager.service.files.ContentService;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Slf4j
@Service
public class StorageFiles implements StorageService {
    private final ContentService contentService;
    private final Path rootLocation;

    @Autowired
    public StorageFiles(ContentService contentService, StorageProperties properties) {
        this.contentService = contentService;
        if(properties.getLocation().trim().isEmpty()){
            throw new StorageException("root location length 0");
        }
        this.rootLocation = Paths.get(properties.getLocation());
    }


    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (Exception ex){
            log.info(ex.getMessage());
        }
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException ex){
            log.info("error: load files");
            throw new StorageException(ex.getMessage());
        }
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());

            if(!resource.exists() || !resource.isReadable()){
                throw new StorageFileNotFoundException("Could not read file: " + fileName);
            }

            return resource;
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public InputStreamResource loadAsRange(File file, long start, long end) throws IOException{

        FileInputStream fis = new FileInputStream(file){
            private long position = start;

            @Override
            public synchronized int read( byte[] b, int off, int len) throws IOException {
                // Check if we reached the end of the specified range
                if(position >= end +1 ){
                    return  -1;
                }

                int byteLength =(int)Math.min(len,end+1 - position);
                int readLength = super.read(b,off,byteLength);
                if(readLength != -1){
                    position += readLength;
                }
                return readLength;
            }

            @Override
            public synchronized int read() throws IOException {
                if (position >= end + 1) {
                    return -1; // End of stream
                }

                int byteRead = super.read();
                if (byteRead != -1) {
                    position++; // Update the current position
                }
                return byteRead;
            }


        };

        return new InputStreamResource(fis);
    }

    @Override
    public Content store(MultipartFile file) {
        if(file.isEmpty()){
            throw new StorageException("body is not exist in sending file");
        }
        Path destination = validPath(file.getOriginalFilename());

        try(InputStream fileStream = file.getInputStream()) {
            Files.copy(fileStream,destination, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException exception){
            log.info("error while writing");
            throw new StorageException("error while writing",exception);
        }

        ContentDTO contentDTO = ContentDTO.builder()
                .type(file.getContentType())
                .sizeByte(file.getSize())
                .srcContent(file.getOriginalFilename())
                .build();
        return contentService.save(contentDTO);
    }

    @Override
    public void deleteAll() {
        try {
            FileSystemUtils.deleteRecursively(rootLocation);
        }
        catch (IOException ex){
            log.info(ex.getMessage());
            throw new StorageException("files is not delete",ex);
        }
    }

    @Override
    public boolean delete(String fileName) {
        Path distention = validPath(fileName);

        File file = distention.toFile();

        if(!file.exists()) {
            log.info("not delete file");
            throw new StorageFileNotFoundException("file is not exist");
        }

        return file.delete();
    }

    @Override
    public void progressUnload(MultipartFile file, String fileId,int startByte) {
        String[] filesParts = fileId.split("-");

        if (filesParts.length != 3) {
            throw new RuntimeException("Invalid fileId format");
        }

        String filename = filesParts[0];
        long size = Long.parseLong(filesParts[1]);
        boolean isLast = Boolean.parseBoolean(filesParts[2]);

        Path filePath = validPath(filename);
        File fileFind = filePath.toFile();

        if (fileFind.exists() && startByte == 0) {
            throw new StorageException("File already exists");
        }


        log.info("parse string in byte size:{}",file.getSize());

        try (FileOutputStream fos = new FileOutputStream(fileFind, startByte != 0)) {
                fos.write(file.getBytes(), 0, file.getBytes().length);
        } catch (IOException ex) {
            log.info("Progress error: {}", ex.getMessage());
            throw new StorageException("Error writing file", ex);
        }

        if(isLast){
            contentService.save(ContentDTO.builder()
                    .type(file.getContentType())
                    .srcContent(file.getOriginalFilename())
                    .sizeByte(size)
                    .build());
        }
    }


    @Override
    public Path validPath(String filename) {
        Path destination = rootLocation.resolve(Paths.get(filename))
                .normalize()
                .toAbsolutePath();

        if(!destination.getParent().equals(rootLocation.toAbsolutePath())){
            log.info("not valid path:{}",filename);
            throw new StorageException("Cannot store file outside current directory.");
        }

        return  destination;
    }
}




