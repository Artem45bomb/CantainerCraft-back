package org.containercraft.servicefilemanager.controller;

import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.containercraft.servicefilemanager.service.files.ContentService;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class FileUploadController {

    private final StorageService storageService;
    private final ContentService contentService;

    @Autowired
    public FileUploadController(StorageService storageService, ContentService contentService) {
        this.storageService = storageService;
        this.contentService = contentService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    @ResponseBody
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
    }

    @PostMapping("/progress/upload")
    @ResponseBody
    public  void progressFileUpload(@RequestHeader("X-File-Id") String fileId,
                                      @RequestHeader("X-Start-Byte") Integer startByte,
                                      @RequestParam("file") MultipartFile file){
        storageService.progressUnload(file,fileId,startByte);
    }

}