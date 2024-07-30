package org.cantainercraft.messenger.controller.graphql;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.ChatSecuredDTO;
import org.cantainercraft.messenger.service.ChatSecuredService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GChatSecuredController {
    private final ChatSecuredService service;

    @QueryMapping
    public List<ChatSecuredDTO> findByUserIdChatSecured(@Argument Long userId){
        return service.findByUserIdChatSecured(userId);
    }
}
