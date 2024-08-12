package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.ContentDTO;
import org.cantainercraft.micro.chats.service.ContentService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Content;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
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
    public ResponseEntity<Content> save(@RequestBody ContentDTO contentDTO) {
        return ResponseEntity.ok(service.save(contentDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Content> update(@RequestBody ContentDTO contentDTO) {
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
