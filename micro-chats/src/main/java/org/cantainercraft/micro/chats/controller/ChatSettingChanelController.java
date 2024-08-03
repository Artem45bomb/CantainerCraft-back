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

    private final ChatSettingsChanelService chatSettingsChanelService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Chat_Settings_Chanel> save(@RequestBody ChatSettingsChanelDTO chatSettingsChanelDTO){

        return ResponseEntity.ok(chatSettingsChanelService.save(chatSettingsChanelDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete/chat_settings_chanel")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid) {
        Optional<Chat_Settings_Chanel> chatSettings = chatSettingsChanelService.findByUUID(uuid);

        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No SettingsChanel to delete");
        }
        return ResponseEntity.ok(chatSettingsChanelService.delete(uuid));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChatSettingsChanelDTO chatSettingsDTO) {
        if (chatSettingsDTO.getUuid() == null) {
            throw new ExistResourceException("No SettingsChanel to update");
        }
        //chatSettingsService.update(chatSettingsDTO);
        return ResponseEntity.ok(chatSettingsChanelService.update(chatSettingsDTO));
    }


    @PostMapping("/id")
    public ResponseEntity<Chat_Settings_Chanel> findByUUID(UUID uuid) {
        Optional<Chat_Settings_Chanel> chatSettings = chatSettingsChanelService.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No SettingsChanel");
        }
        return ResponseEntity.ok(chatSettings.get());
    }

}
