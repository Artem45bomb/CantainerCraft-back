package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
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

    private final ChatSettingsService chatSettingsService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Chat_Settings> save(@RequestBody ChatSettingsDTO chatSettingsDTO)
    {
        Optional<Chat_Settings> chatSettings = chatSettingsService.findByUUID(chatSettingsDTO.getUuid());

        if (chatSettings.isPresent()) {
            throw new ExistResourceException("Setting is exist");
        }
        return ResponseEntity.ok(chatSettingsService.save(chatSettingsDTO));
    }


    @PutMapping("/delete/chat_settings")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid) {
        Optional<Chat_Settings> chatSettings = chatSettingsService.findByUUID(uuid);

        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No Settings to delete");
        }
        return ResponseEntity.ok(chatSettingsService.delete(uuid));
    }


    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChatSettingsDTO chatSettingsDTO) {
        if (chatSettingsDTO.getUuid() == null) {
            throw new ExistResourceException("No Settings to update");
        }
        //chatSettingsService.update(chatSettingsDTO);
        return ResponseEntity.ok(chatSettingsService.update(chatSettingsDTO));
    }

    @PostMapping("/id")
    public ResponseEntity<Chat_Settings> findByUUID(UUID uuid) {
        Optional<Chat_Settings> chatSettings = chatSettingsService.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No Settings");
        }
        return ResponseEntity.ok(chatSettings.get());
    }


    @PostMapping("/search")
    public ResponseEntity<List<Chat_Settings>> findByChatID(UUID uuid) {
        return ResponseEntity.ok(chatSettingsService.findByChatId(uuid));
    }
}

