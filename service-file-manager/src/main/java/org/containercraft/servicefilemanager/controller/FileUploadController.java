package org.containercraft.servicefilemanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<InputStreamResource> downloadFIle(
            @PathVariable String filename,
            @RequestHeader HttpHeaders headers) throws IOException
    {
        File file = storageService.validPath(filename).toFile();
        long fileLength = file.length();

        List<HttpRange> httpRanges = headers.getRange();
        if(httpRanges.isEmpty()){
            log.info("header range is empty");
             return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .header(HttpHeaders.CONTENT_RANGE,"bytes="+0+"-"+fileLength+"/"+fileLength)
                    .header(HttpHeaders.CONTENT_LENGTH,String.valueOf(fileLength))
                    .body(new InputStreamResource(new FileInputStream(file)));
        }
        else {
            HttpRange httpRange = httpRanges.get(0);
            long start = httpRange.getRangeStart(fileLength);
            long end = httpRange.getRangeEnd(fileLength);
            long rangeLength = end - start +1;

            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .header(HttpHeaders.CONTENT_RANGE,"bytes="+start+"-"+end+"/"+rangeLength)
                    .header(HttpHeaders.CONTENT_LENGTH,String.valueOf(rangeLength))
                    .body(storageService.loadAsRange(file,start,end));
        }
    }


    @PostMapping("/")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
    }

    @PostMapping("/progress/upload")
    public  void progressFileUpload(@RequestHeader("X-File-Id") String fileId,
                                      @RequestHeader("X-Start-Byte") Integer startByte,
                                      @RequestParam("file") MultipartFile file){
        storageService.progressUnload(file,fileId,startByte);
    }

}