package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.MessageReadDTO;
import org.cantainercraft.micro.chats.service.MessageReadService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.MessageRead;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/message-reads")
@RequiredArgsConstructor
public class MessageReadController {

    private final MessageReadService service;

    @PostMapping("/all")
    public ResponseEntity<List<MessageRead>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<MessageRead> findById(@PathVariable UUID id) {
        Optional<MessageRead> messageRead = service.findById(id);
        if(messageRead.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(messageRead.get());
    }

    @PostMapping("/add")
    public ResponseEntity<MessageRead> create(@RequestBody MessageReadDTO messageReadDTO) {
        if (service.findById(messageReadDTO.getUuid()).isPresent()) {
            throw new ExistResourceException("Content is already exist");
        }
        return ResponseEntity.ok(service.save(messageReadDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody MessageReadDTO messageReadDTO) {
        if (service.findById(messageReadDTO.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(messageReadDTO));
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody UUID uuid) {
        if (service.findById(uuid).isEmpty()) {
           throw new NotResourceException("No content to delete");
        }
        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
