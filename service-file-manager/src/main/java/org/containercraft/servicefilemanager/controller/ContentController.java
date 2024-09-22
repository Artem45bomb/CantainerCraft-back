package org.containercraft.servicefilemanager.controller;

import lombok.RequiredArgsConstructor;

import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.containercraft.servicefilemanager.service.files.ContentService;
import org.containercraft.servicefilemanager.service.files.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService service;
    private final StorageService storageService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Content>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Content> findById(@PathVariable UUID uuid) {
        Optional<Content> content = service.findById(uuid);
        if(content.isEmpty() || content.get().isDelete()){
            throw new StorageFileNotFoundException("No content");
        }

        return ResponseEntity.ok(content.get());
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<Content> findByFile(@PathVariable String filename){
        Optional<Content> content = service.findBySrc(storageService.validPath(filename).toString());
        if(content.isEmpty() || content.get().isDelete()){
            throw new StorageFileNotFoundException("No content");
        }

        return ResponseEntity.ok(content.get());
    }

    @GetMapping("/exist/{uuid}")
    public Boolean existById(@PathVariable UUID uuid){
        return service.existById(uuid);
    }

}
