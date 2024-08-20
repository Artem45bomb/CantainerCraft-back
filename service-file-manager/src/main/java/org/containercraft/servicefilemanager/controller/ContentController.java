package org.containercraft.servicefilemanager.controller;

import lombok.RequiredArgsConstructor;

import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.service.files.ContentService;
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
    
    @GetMapping("/all")
    public ResponseEntity<List<Content>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Content> findById(@PathVariable UUID uuid) {
        Optional<Content> content = service.findById(uuid);
        if(content.isEmpty() || content.get().isDelete()){
            throw new StorageException("No content");
        }

        return ResponseEntity.ok(content.get());
    }

    @GetMapping("/src/{src}")
    public ResponseEntity<Content> findBySrc(@PathVariable String src){
        Optional<Content> content = service.findBySrc(src);

        if(content.isEmpty() || content.get().isDelete()){
            throw new StorageException("No content");
        }

        return ResponseEntity.ok(content.get());
    }

}
