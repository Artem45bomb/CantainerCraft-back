package org.cantainercraft.micro.users.controller;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.service.ChatInfoService;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.cantainercraft.project.entity.users.Profile;
import org.cantainercraft.project.entity.users.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/chatInfo")
@RequiredArgsConstructor
public class ChatInfoController  {

    private final ChatInfoService chatInfoService;

    @PostMapping("/add")
    public ResponseEntity<Chat_Info> save(@RequestBody ChatInfoDTO chatInfoDTO){
        return ResponseEntity.ok(chatInfoService.save(chatInfoDTO));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Chat_Info> findById(@PathVariable UUID uuid){
        return ResponseEntity.ok(chatInfoService.findById(uuid));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Chat_Info>> findAll(){
        return ResponseEntity.ok(chatInfoService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<Chat_Info> update(@RequestBody ChatInfoDTO chatInfoDTO) {
        return ResponseEntity.ok(chatInfoService.update(chatInfoDTO));
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid ){
        chatInfoService.deleteById(uuid);
    }



}
