package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.dto.ChatSettingsGroupDTO;
import org.cantainercraft.micro.chats.service.ChatSettingsGroupService;
import org.cantainercraft.micro.chats.service.impl.ChatSettingsGroupServiceImpl;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Chat_Settings_Group")
@Slf4j
@RequiredArgsConstructor
public class ChatSettingsGroupController {

    private final ChatSettingsGroupServiceImpl chatSettingsGroupServiceImpl;
    private final ChatSettingsGroupService chatSettingsGroupService;

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings_Group> findBuUUID(@RequestBody UUID uuid)
    {
        Optional<Chat_Settings_Group> chatSettingsGroup = chatSettingsGroupServiceImpl.findByUUID(uuid);
        if(chatSettingsGroup.isEmpty())
        {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsGroup.get());
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PostMapping("/uuid")
    public ResponseEntity<Chat_Settings_Group> findByGroupId(@RequestBody UUID uuid)
    {
        Optional<Chat_Settings_Group> chatSettingsGroup = chatSettingsGroupServiceImpl.findByUUID(uuid);
        if(chatSettingsGroup.isEmpty())
        {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsGroup.get());
    }


    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody UUID uuid)
    {
        Optional<Chat_Settings_Group> chatSettings = chatSettingsGroupService.findByUUID(uuid);
        if(chatSettings.isEmpty())
        {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsGroupService.delete(uuid));
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ChatSettingsGroupDTO chatSettingsGroupDTO){
        Optional<Chat_Settings_Group> chatSettings = chatSettingsGroupService.findByUUID(chatSettingsGroupDTO.getUuid());
        if (chatSettings.isEmpty())
        {
            throw new NotResourceException("NoN");
        }
        if(chatSettingsGroupDTO.getUuid()==null)
        {
            throw new NotResourceException("NoN");
        }else{
            return ResponseEntity.ok(chatSettingsGroupService.update(chatSettingsGroupDTO));
        }
    }

    @PreAuthorize("hasAnyRole('USER,ADMIN')") //<<<--------------------
    @PutMapping("/add")
    public ResponseEntity<Chat_Settings_Group> save(@RequestBody ChatSettingsGroupDTO chatSettingsGroupDTO) {
        Optional<Chat_Settings_Group> chatSettingsGroup = chatSettingsGroupService.findByUUID(chatSettingsGroupDTO.getSettingGroupId());

        if(chatSettingsGroup.isPresent())
        {
            throw new NotResourceException("NoN");
        }
        return ResponseEntity.ok(chatSettingsGroupService.save(chatSettingsGroupDTO));
    }
}
