
package org.cantainercraft.micro.chats.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.micro.chats.service.EmotionService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/emotions")
@RequiredArgsConstructor
public class EmotionController {
    public final EmotionService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete/id")
    public void deleteById(@RequestBody UUID uuid){
        Optional<Emotion> emotion = service.findById(uuid);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for delete");
        }
        service.deleteById(uuid);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete")
    public void deleteByUnicode(@RequestBody String unicode) {
        Optional<Emotion> emotion = service.findByUnicode(unicode);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for delete");
        }
        service.deleteByUnicode(unicode);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Emotion> save(@Valid @RequestBody EmotionDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Emotion> update(@Valid @RequestBody EmotionDTO dto){
        Optional<Emotion> emotion = service.findById(dto.getUuid());
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for update");
        }
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Emotion>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/id/{uuid}")
    public ResponseEntity<Emotion> findById(@PathVariable UUID uuid){
        Optional<Emotion> emotion = service.findById(uuid);
        if (emotion.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(emotion.get());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/unicode/{unicode}")
    public ResponseEntity<Emotion> findByUnicode(@PathVariable String unicode){
        Optional<Emotion> emotion = service.findByUnicode(unicode);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(emotion.get());
    }
}

