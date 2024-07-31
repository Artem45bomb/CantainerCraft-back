package org.cantainercraft.micro.chats.controller;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.service.ChatImageProfileService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat-image-profiles")
@RequiredArgsConstructor
public class ChatImageProfileController {

    private final ChatImageProfileService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Chat_Image_Profile>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Chat_Image_Profile> findById(@RequestBody UUID uuid) {
        Optional<Chat_Image_Profile> chatImageProfile = service.findById(uuid);

        if(chatImageProfile.isEmpty()) throw new NotResourceException("No content");

        return ResponseEntity.ok(chatImageProfile.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Chat_Image_Profile> save(@RequestBody ChatImageProfileDTO dto) {
        if(dto.getSrcContent() == null || dto.getSrcContent().isEmpty()) {
            throw new NotValidateParamException("missed parma: srcContent");
        }
        
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChatImageProfileDTO dto) {
        if(dto.getSrcContent() == null || dto.getSrcContent().isEmpty()) {
            throw new NotValidateParamException("missed parma: srcContent");
        }

        if (!service.existById(dto.getUuid())) throw new NotResourceException("No content to update");

        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody UUID id) {
        if (!service.existById(id)) throw new NotResourceException("No content to update");

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
