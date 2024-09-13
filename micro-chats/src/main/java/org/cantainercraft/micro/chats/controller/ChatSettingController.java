package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_settings")
@RequiredArgsConstructor
public class ChatSettingController {
    private final ChatSettingsService service;


    @GetMapping("/{uuid}")
    public ResponseEntity<Chat_Settings> findById(@PathVariable UUID uuid) {
        Optional<Chat_Settings> chatSettings = service.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No Settings");
        }
        return ResponseEntity.ok(chatSettings.get());
    }


    @PostMapping("/chat/{uuid}")
    public ResponseEntity<Chat_Settings> findByChatId(@PathVariable UUID uuid) {
        Optional<Chat_Settings> settings = service.findByChatId(uuid);

        if(settings.isEmpty())
            throw new NotResourceException("settings is not exist");

        return ResponseEntity.ok(settings.get());
    }

    @PostMapping("/all")
    public List<Chat_Settings> findAll(){
        return service.findAll();
    }

}

