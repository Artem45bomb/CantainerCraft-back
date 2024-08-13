package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.ChatSettingsGroupDTO;
import org.cantainercraft.micro.chats.service.ChatSettingsGroupService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_settings_group")
@RequiredArgsConstructor
public class ChatSettingsGroupController {

    private final ChatSettingsGroupService service;


    @PutMapping("/update")
    public ResponseEntity<Chat_Settings_Group> update(@RequestBody ChatSettingsGroupDTO chatSettingsDTO) {
        if (chatSettingsDTO.getUuid() == null) {
            throw new ExistResourceException("No SettingsGroup to update");
        }
        return ResponseEntity.ok(service.update(chatSettingsDTO));
    }

    @PostMapping("/id")
    public ResponseEntity<Chat_Settings_Group> findByUUID(UUID uuid) {
        Optional<Chat_Settings_Group> chatSettings = service.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No SettingsGroup");
        }
        return ResponseEntity.ok(chatSettings.get());
    }

    @PostMapping("/all")
    public List<Chat_Settings_Group> findAll() {
        return service.findAll();
    }
}
