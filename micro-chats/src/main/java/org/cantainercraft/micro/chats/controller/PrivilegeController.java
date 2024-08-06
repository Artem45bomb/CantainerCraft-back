package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.dto.PrivilegeSearchDTO;
import org.cantainercraft.micro.chats.service.PrivilegeService;
import org.cantainercraft.project.entity.chats.Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
//import java.util.Optional;

@RestController
@RequestMapping("/api/privileges")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService service;

    @PostMapping("/all")
    public ResponseEntity<List<Privilege>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //optional
    @PostMapping("/uuid")
    public ResponseEntity<Privilege> findById(@RequestBody UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }


    //без имени
    @PostMapping("/user")
    public ResponseEntity<List<Privilege>> findByChat(@RequestBody PrivilegeSearchDTO dto) {
        UUID chatId = dto.getChatId();
        String chatName = dto.getChatName();

        return ResponseEntity.ok(service.findByChat(chatId, chatName));
    }

    @PostMapping("/add")
    public ResponseEntity<Privilege> save(@RequestBody PrivilegeDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Privilege> update(@RequestBody PrivilegeDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid) {
        service.delete(uuid);
    }
}
