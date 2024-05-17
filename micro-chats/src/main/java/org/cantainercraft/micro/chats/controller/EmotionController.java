
package org.cantainercraft.micro.chats.controller;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.micro.chats.service.EmotionService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Emotion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/emotions")
@RequiredArgsConstructor
public class EmotionController {
    public final EmotionService service;

    @PutMapping("/delete/id")
    public void deleteById(@RequestBody UUID uuid){
        Optional<Emotion> emotion = service.findById(uuid);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for delete");
        }
        service.deleteById(uuid);
    }

    @PutMapping("/delete")
    public void deleteByUnicode(@RequestBody String unicode) {
        Optional<Emotion> emotion = service.findByUnicode(unicode);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for delete");
        }
        service.deleteByUnicode(unicode);
    }

    @PostMapping("/add")
    public ResponseEntity<Emotion> save(@RequestBody EmotionDTO emotionDTO){
        Optional<Emotion> emotion = service.findById(emotionDTO.getUuid());
        if (!emotion.isEmpty()) {
            throw new ExistResourceException("Emotion is exist");
        }
        return ResponseEntity.ok(service.save(emotionDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Emotion> update(@RequestBody EmotionDTO emotionDTO){
        Optional<Emotion> emotion = service.findById(emotionDTO.getUuid());
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for update");
        }
        return ResponseEntity.ok(service.update(emotionDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Emotion>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/id")
    public ResponseEntity<Emotion> findById(UUID uuid){
        Optional<Emotion> emotion = service.findById(uuid);
        if (emotion.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(emotion.get());
    }

    @GetMapping
    public ResponseEntity<Emotion> findByUnicode(String unicode){
        Optional<Emotion> emotion = service.findByUnicode(unicode);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(emotion.get());
    }
}

