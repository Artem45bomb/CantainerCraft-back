package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.dto.ChatSettingsUpdateDTO;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.micro.chats.service.impl.ChatSettingsServiceImpl;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Chat_Settings")
@Slf4j
@RequiredArgsConstructor
public class ChatSettingController
{
    private final ChatSettingsServiceImpl chatSettingsServiceImpl;
    private final ChatSettingsService chatSettingsService;

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings> findByUUID(@RequestBody UUID uuid) {
        Optional<Chat_Settings> chatSettings = chatSettingsServiceImpl.findByUUID(uuid);
        if(chatSettings.isEmpty()){
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettings.get());
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings> findByChatId(@RequestBody UUID uuid) {
        Optional<Chat_Settings> chatSettings = chatSettingsServiceImpl.findByUUID(uuid);
        if(chatSettings.isEmpty()) {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettings.get());
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid)
    {
        Optional<Chat_Settings> chatSettings = chatSettingsService.findByUUID(uuid);
        if(chatSettings.isEmpty())
        {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsService.delete(uuid));
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChatSettingsUpdateDTO chatSettingsUpdateDTO){
        Optional<Chat_Settings> chatSettings = chatSettingsService.findByUUID(chatSettingsUpdateDTO.getUuid());
        if (chatSettings.isEmpty())
        {
            throw new NotResourceException("NoN");
        }
        if(chatSettingsUpdateDTO.getUuid()== null)
        {
            throw new NotResourceException("NoN");
        }else{
            return ResponseEntity.ok(chatSettingsService.update(chatSettingsUpdateDTO));
        }
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PutMapping("/add")
    public ResponseEntity<Chat_Settings> save(@RequestBody ChatSettingsDTO chatSettingsDTO) {
        Optional<Chat_Settings> chatSettings = chatSettingsService.findByUUID(chatSettingsDTO.getUuid());

        if(chatSettings.isPresent())
        {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsService.save(chatSettingsDTO));
    }


}
