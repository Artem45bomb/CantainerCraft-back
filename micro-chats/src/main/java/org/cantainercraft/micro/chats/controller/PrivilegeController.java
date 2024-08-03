package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.dto.PrivilegeSearchDTO;
import org.cantainercraft.micro.chats.service.PrivilegeService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/privileges")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService service;

    @PostMapping("/all")
    public ResponseEntity<List<Privilege>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Privilege> findById(@RequestBody UUID id) {
        Optional<Privilege> privilege = service.findById(id);
        return ResponseEntity.ok(privilege.get());
    }

    @PostMapping("/user")
    public ResponseEntity<List<Privilege>> findByChat(@RequestBody PrivilegeSearchDTO privilegeSearchDTO) {
        UUID chatId = privilegeSearchDTO.getChatId();
        String chatName = privilegeSearchDTO.getChatName();

        List<Privilege> privileges = service.findByChat(chatId, chatName);
        if (privileges.isEmpty()) throw new NotResourceException("No content");

        return ResponseEntity.ok(privileges);
    }

    @PostMapping("/add")
    public ResponseEntity<Privilege> save(@RequestBody PrivilegeDTO dto) {
        if(service.findByChatIdAndNameRole(dto.getChat().getUuid(),dto.getNameRole())){
            throw new  ExistResourceException("content is exist");
        }
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody PrivilegeDTO dto) {
        if (service.findById(dto.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody UUID id) {
        if (service.findById(id).isEmpty()) {
            throw new NotResourceException("No content to delete");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
