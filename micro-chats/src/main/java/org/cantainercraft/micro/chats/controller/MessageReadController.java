package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.MessageReadDTO;
import org.cantainercraft.micro.chats.service.MessageReadService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.MessageRead;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/message-reads")
@RequiredArgsConstructor
public class MessageReadController {

    private final MessageReadService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<MessageRead>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<MessageRead> findById(@RequestBody UUID id) {
        Optional<MessageRead> messageRead = service.findById(id);
        if(messageRead.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(messageRead.get());
    }

    @PostMapping("/add")
    public ResponseEntity<MessageRead> save(@RequestBody MessageReadDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageRead> update(@RequestBody MessageReadDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid) {
        service.delete(uuid);
    }
}
