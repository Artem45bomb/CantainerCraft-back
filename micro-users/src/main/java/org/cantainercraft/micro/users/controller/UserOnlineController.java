package org.cantainercraft.micro.users.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user_online")
public class UserOnlineController {
    private final UserOnlineService service;

    @GetMapping("/all")
    public ResponseEntity<List<User_Online>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User_Online> findById(@PathVariable UUID uuid) {
        Optional<User_Online> userOnline =service.findById(uuid);

        if (userOnline.isEmpty()) {
            throw new NotResourceException("User online not found");
        }

        return ResponseEntity.ok(userOnline.get());
    }

    @PutMapping("/update")
    public ResponseEntity<User_Online> update(@Valid @RequestBody UserOnlineDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }
}
