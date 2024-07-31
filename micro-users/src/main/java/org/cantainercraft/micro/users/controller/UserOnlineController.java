package org.cantainercraft.micro.users.controller;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user_online")
public class UserOnlineController {


    private final UserOnlineService userOnlineService;

    @GetMapping("/all")
    public ResponseEntity<List<User_Online>> findAll(){
        return ResponseEntity.ok(userOnlineService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User_Online> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userOnlineService.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Online> save(@RequestBody UserOnlineDTO dto){
         return ResponseEntity.ok(userOnlineService.save(dto));
    }


    @PutMapping("/update")
    public ResponseEntity<User_Online> update(@RequestBody UserOnlineDTO dto){
        return ResponseEntity.ok(userOnlineService.update(dto));
    }


    @DeleteMapping("/delete/id")
    public void deleteById(@RequestBody UUID uuid ){

        userOnlineService.deleteById(uuid);

    }

}
