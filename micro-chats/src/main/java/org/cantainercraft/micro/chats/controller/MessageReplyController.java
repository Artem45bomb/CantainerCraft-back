package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.MessageReplyDTO;
import org.cantainercraft.micro.chats.service.MessageReplyService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Message_Reply;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/message-replies")
@RequiredArgsConstructor
public class MessageReplyController {

    private final MessageReplyService service;

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
    public ResponseEntity<Message_Reply> save(@RequestBody MessageReplyDTO messageReplyDTO) {
        if (service.findById(messageReplyDTO.getUuid()).isPresent()) {
            throw new ExistResourceException("Content is already exist");
        }
        return ResponseEntity.ok(service.save(messageReplyDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody MessageReplyDTO messageReplyDTO) {
        if (service.findById(messageReplyDTO.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(messageReplyDTO));
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody UUID id) {
        if (service.findById(id).isEmpty()) {
            throw new NotResourceException("No content to delete");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/delete/user")
    public ResponseEntity<Void> deleteByMessageReplyUserId(@RequestBody MessageReplyDTO messageReplyDTO) {
        if (service.findByMessageReplyUserId(messageReplyDTO.getMessageReply().getUserId()).isEmpty()) {
           throw new NotResourceException("No content to delete");
        }
        service.deleteByMessageReplyUserId(messageReplyDTO.getMessageReply().getUserId());
        return ResponseEntity.noContent().build();
    }
}
