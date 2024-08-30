package org.cantainercraft.micro.users.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.service.ChatInfoService;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat-info")
@RequiredArgsConstructor
public class ChatInfoController  {

    private final ChatInfoService service;

    @PostMapping("/add")
    public ResponseEntity<Chat_Info> save(@Valid @RequestBody ChatInfoDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Chat_Info> findById(@PathVariable UUID uuid){
        return ResponseEntity.ok(service.findById(uuid));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Chat_Info>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Chat_Info> update(@Valid @RequestBody ChatInfoDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid ){
        service.deleteById(uuid);
    }



}
