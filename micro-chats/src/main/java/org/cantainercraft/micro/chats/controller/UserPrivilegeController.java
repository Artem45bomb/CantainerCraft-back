package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.service.UserPrivilegeService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-privileges")
@RequiredArgsConstructor
public class UserPrivilegeController {

    private final UserPrivilegeService service;

    @PostMapping("/all")
    public ResponseEntity<List<User_Privilege>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<User_Privilege> findById(@RequestBody UUID id) {
        Optional<User_Privilege> userPrivilege = service.findById(id);
        if(userPrivilege.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(userPrivilege.get());
    }

    @PostMapping("/userId")
    public ResponseEntity<User_Privilege> findByUserId(Long id) {
        Optional<User_Privilege> userPrivilege = service.findByUserId(id);
        if (userPrivilege.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(userPrivilege.get());
    }

    @PostMapping("/add")
    public ResponseEntity<User_Privilege> create(@RequestBody UserPrivilegeDTO userPrivilegeDTO) {
        if (service.findById(userPrivilegeDTO.getUuid()).isPresent()) {
            throw new ExistResourceException("Content is already exist");
        }
        return ResponseEntity.ok(service.save(userPrivilegeDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody UserPrivilegeDTO userPrivilegeDTO) {
        if (service.findById(userPrivilegeDTO.getUuid()).isEmpty()) {
            throw new NotResourceException("No content to update");
        }
        return ResponseEntity.ok(service.update(userPrivilegeDTO));
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
    public ResponseEntity<Void> deleteByUserId(@RequestBody Long id) {
        if (service.findByUserId(id).isEmpty()) {
            throw new NotResourceException("No content to delete");
        }
        service.deleteByUserId(id);
        return ResponseEntity.noContent().build();
    }
}
