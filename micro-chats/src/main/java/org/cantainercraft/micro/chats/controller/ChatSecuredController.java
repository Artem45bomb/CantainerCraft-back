package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSecuredDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.service.ChatSecuredService;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_secured")
@RequiredArgsConstructor
public class ChatSecuredController {
    private final ChatSecuredService service;
    private final ChatSecuredDTOConvertor convertor;

    @PostMapping("/add")
    public ChatSecuredDTO save(@RequestBody ChatSecuredDTO dto){
        Chat_Secured entity = service.save(dto);
        ChatSecuredDTO chatSecured = convertor.convertEntityToDTO(entity);
        chatSecured.setChatId(entity.getChat().getUuid());
        return chatSecured;
    }

    @PutMapping("/id")
    public void deleteById(@RequestBody UUID id){
        service.deleteById(id);
    }

    @PutMapping
    public void delete(@RequestBody ChatSecuredDTO dto){
        service.delete(dto);
    }

    @GetMapping("/all")
    public List<ChatSecuredDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<ChatSecuredDTO> findByUserId(@PathVariable Long userId){
        return service.findByUserId(userId);
    }
}
