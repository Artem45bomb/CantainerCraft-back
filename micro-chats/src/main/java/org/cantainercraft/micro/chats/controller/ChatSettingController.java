package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_settings")
@RequiredArgsConstructor
public class ChatSettingController {
    private final ChatSettingsService service;


    @PostMapping("/id")
    public ResponseEntity<Chat_Settings> findByUUID(UUID uuid) {
        Optional<Chat_Settings> chatSettings = service.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No Settings");
        }
        return ResponseEntity.ok(chatSettings.get());
    }


    @PostMapping("/search")
    public ResponseEntity<List<Chat_Settings>> findByChatID(UUID uuid) {
        return ResponseEntity.ok(service.findByChatId(uuid));
    }

    @PostMapping("/all")
    public List<Chat_Settings> findAll(){
        return service.findAll();
    }

}

