package org.cantainercraft.micro.chats.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.chats.service.ChatSettingsChanelService;
import org.cantainercraft.micro.chats.service.impl.ChatSettingsChanelServiceImpl;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Chat_Settings")
@Slf4j
@RequiredArgsConstructor
public class ChatSettingsChanelController {

    private final ChatSettingsChanelService chatSettingsChanelService;
    private final ChatSettingsChanelServiceImpl chatSettingsChanelServiceImpl;

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings_Chanel> findByUUID(@RequestBody UUID uuid) {
        Optional<Chat_Settings_Chanel> chatSettingsChanel = chatSettingsChanelServiceImpl.findByUUID(uuid);
        if(chatSettingsChanel.isEmpty()) {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsChanel.get());
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings_Chanel> findByChatId(@RequestBody UUID uuid) {
        Optional<Chat_Settings_Chanel> chatSettingsChanel = chatSettingsChanelServiceImpl.findByUUID(uuid);
        if(chatSettingsChanel.isEmpty()) {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsChanel.get());
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid) {
        Optional<Chat_Settings_Chanel> chatSettingsChanel = chatSettingsChanelService.findByUUID(uuid);
        if(chatSettingsChanel.isEmpty()) {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsChanelService.delete(uuid));
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Boolean> update(@RequestBody ChatSettingsChanelDTO chatSettingsChanelDTO) {
        Optional<Chat_Settings_Chanel> chatSettingsChanel = chatSettingsChanelService.findByUUID(chatSettingsChanelDTO.getUuid());
        if(chatSettingsChanel.isEmpty()) {
            throw new NotResourceException("NoN");
        }
        if(chatSettingsChanelDTO.getUuid() == null){
            throw new NotResourceException("NoN");
        } else {
            return ResponseEntity.ok(chatSettingsChanelService.update(chatSettingsChanelDTO));
        }
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings_Chanel> save(@RequestBody ChatSettingsChanelDTO chatSettingsChanelDTO) {
        Optional<Chat_Settings_Chanel> chatSettingsChanel = chatSettingsChanelService.findByUUID(chatSettingsChanelDTO.getChatSettings().getUuid());
        if(chatSettingsChanel.isPresent()) {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsChanelService.save(chatSettingsChanelDTO));
    }
}



