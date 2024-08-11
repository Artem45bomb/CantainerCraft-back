package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.MessageReplyDTO;
import org.cantainercraft.micro.chats.service.MessageReplyService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message_Reply;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/message-replies")
@RequiredArgsConstructor
public class MessageReplyController {

    private final MessageReplyService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Message_Reply>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Message_Reply> findById(@RequestBody UUID uuid) {
        Optional<Message_Reply> messageReply = service.findById(uuid);
        if(messageReply.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(messageReply.get());
    }

    @PostMapping("/add")
    public ResponseEntity<Message_Reply> save(@RequestBody MessageReplyDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Message_Reply> update(@RequestBody MessageReplyDTO dto) {
        if (service.findById(dto.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        if (service.findById(id).isEmpty()) {
            throw new NotResourceException("No content to delete");
        }
        service.delete(id);
    }

    @PutMapping("/delete/user")
    public void deleteByMessageReplyUserId(@RequestBody MessageReplyDTO dto) {
        if (service.findByMessageReplyUserId(dto.getMessageReply().getUserId()).isEmpty()) {
           throw new NotResourceException("No content to delete");
        }
        service.deleteByMessageReplyUserId(dto.getMessageReply().getUserId());
    }
}
