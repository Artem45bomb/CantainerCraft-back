package org.cantainercraft.micro.chats.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.service.ChatSecuredService;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_secured")
@RequiredArgsConstructor
public class ChatSecuredController {
    private final ChatSecuredService service;
    private final ConvertorDTO<ChatSecuredDTO, Chat_Secured> convertor;

    @PostMapping("/add")
    public ChatSecuredDTO save(@Valid @RequestBody ChatSecuredDTO dto){
        Chat_Secured entity = service.save(dto);
        ChatSecuredDTO chatSecured = convertor.convertEntityToDTO(entity);

        //mapper is not convert chatId
        chatSecured.setChatId(entity.getChat().getUuid());
        return chatSecured;
    }

    @PutMapping("/id")
    public void deleteById(@RequestBody UUID id){
        service.deleteById(id);
    }

    @PutMapping
    public void delete(@Valid @RequestBody ChatSecuredDTO dto){
        service.delete(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all")
    public List<ChatSecuredDTO> findAll(){
        return service.findAll();
    }

//    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/user/{userId}")
    public List<ChatSecuredDTO> findByUserId(@PathVariable Long userId){
        return service.findByUserId(userId);
    }
}
