package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.micro.chats.service.ContentService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Content;
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

    @PostMapping("/all")
    public ResponseEntity<List<Content>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Content> findById(@RequestBody UUID uuid) {
        Optional<Content> content = service.findById(uuid);
        if(content.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(content.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Content> create(@RequestBody ContentDTO contentDTO) {
        if (service.findById(contentDTO.getUuid()).isPresent()) {
            throw new ExistResourceException("Content is already exist");
        }
        return ResponseEntity.ok(service.save(contentDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ContentDTO contentDTO) {
        if (service.findById(contentDTO.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(contentDTO));
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody UUID id) {
        if (service.findById(id).isEmpty()) {
            throw new NotResourceException("No content to delete");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
