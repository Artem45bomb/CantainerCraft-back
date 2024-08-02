package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.chats.service.ChatSettingsChanelService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_settings_chanel")
@RequiredArgsConstructor
public class ChatSettingChanelController {

    private final ChatSettingsChanelService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Chat_Settings_Chanel> save(@RequestBody ChatSettingsChanelDTO chatSettingsChanelDTO){

        return ResponseEntity.ok(service.save(chatSettingsChanelDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete/chat_settings_chanel")
    public void delete(@RequestBody UUID uuid) {
        service.delete(uuid);
    }

    @PutMapping("/update")
    public ResponseEntity<Chat_Settings_Chanel> update(@RequestBody ChatSettingsChanelDTO chatSettingsDTO) {
        if (chatSettingsDTO.getUuid() == null) {
            throw new ExistResourceException("No SettingsChanel to update");
        }
        //chatSettingsService.update(chatSettingsDTO);
        return ResponseEntity.ok(service.update(chatSettingsDTO));
    }


    @PostMapping("/id")
    public ResponseEntity<Chat_Settings_Chanel> findByUUID(UUID uuid) {
        Optional<Chat_Settings_Chanel> chatSettings = service.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No SettingsChanel");
        }
        return ResponseEntity.ok(chatSettings.get());
    }

}
