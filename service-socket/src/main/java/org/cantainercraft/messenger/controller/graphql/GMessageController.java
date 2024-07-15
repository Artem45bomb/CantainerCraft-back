package org.cantainercraft.messenger.controller.graphql;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.dto.MessageSearchDTO;
import org.cantainercraft.messenger.service.MessageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class GMessageController {
    private final MessageService messageService;

    @QueryMapping
    public MessageDTO messageId(@Argument String id){
        return messageService.findById(UUID.fromString(id));
    }

    @QueryMapping
    public List<MessageDTO> messagesSearch(@Argument MessageSearchDTO input){
        return messageService.findBySearch(input);
    }
}
