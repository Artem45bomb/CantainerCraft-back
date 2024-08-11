package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.MessageForwardDTO;
import org.cantainercraft.micro.chats.service.MessageForwardService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message_Forward;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/message-forwards")
@RequiredArgsConstructor
public class MessageForwardController {

    private final MessageForwardService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Message_Forward>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Message_Forward> findById(@RequestBody UUID id) {
        Optional<Message_Forward> messageForward = service.findById(id);
        if(messageForward.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(messageForward.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Message_Forward> save(@RequestBody MessageForwardDTO messageForwardDTO) {
        return ResponseEntity.ok(service.save(messageForwardDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Message_Forward> update(@RequestBody MessageForwardDTO messageForwardDTO) {
        if (service.findById(messageForwardDTO.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(messageForwardDTO));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid) {
        if (service.findById(uuid).isEmpty()) {
            throw new NotResourceException("No content to delete");
        }
        service.delete(uuid);
    }
}
