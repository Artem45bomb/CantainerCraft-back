package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.EmotionAddDTO;
import org.cantainercraft.micro.chats.dto.EmotionDeleteDTO;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.chats.service.UserEmotionService;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user_emotion")
@RequiredArgsConstructor
public class UserEmotionController {

    private final UserEmotionService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Emotion>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<User_Emotion> findById(@RequestBody UUID uuid) {
        return  ResponseEntity.ok(service.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Emotion> save(@RequestBody UserEmotionDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping("/message/add")
    public ResponseEntity<EmotionAddDTO> addEmotion(@RequestBody EmotionAddDTO dto){
        User_Emotion userEmotion = service.addEmotion(dto);

        return ResponseEntity.ok(EmotionAddDTO.builder()
                        .userId(userEmotion.getUserId())
                        .uuid(userEmotion.getUuid())
                        .emotionId(userEmotion.getEmotion().getUuid())
                        .messageClientId(userEmotion.getMessage().getClientId())
                        .build());
    }

    @PutMapping("/message/delete")
    public void deleteEmotion(@RequestBody EmotionDeleteDTO dto){
        service.deleteEmotion(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<User_Emotion> update(@RequestBody UserEmotionDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        service.delete(id);
    }
}
