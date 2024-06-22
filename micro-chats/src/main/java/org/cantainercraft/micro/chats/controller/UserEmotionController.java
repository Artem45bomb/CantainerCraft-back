package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.EmotionAddDTO;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.chats.service.UserEmotionService;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user_emotion")
@RequiredArgsConstructor
public class UserEmotionController {

    private final UserEmotionService service;

    @PostMapping("/all")
    public ResponseEntity<List<User_Emotion>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<User_Emotion> findById(@RequestBody UUID uuid) {
        return  ResponseEntity.ok(service.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Emotion> save(@RequestBody UserEmotionDTO contentDTO) {
        return ResponseEntity.ok(service.save(contentDTO));
    }

    @PostMapping("/message/add")
    public ResponseEntity<User_Emotion> add(@RequestBody EmotionAddDTO emotionAddDTO){
        return ResponseEntity.ok(service.add(emotionAddDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<User_Emotion> update(@RequestBody UserEmotionDTO contentDTO) {
        return ResponseEntity.ok(service.update(contentDTO));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        service.delete(id);
    }
}
