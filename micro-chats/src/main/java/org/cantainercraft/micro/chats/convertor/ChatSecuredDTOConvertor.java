package org.cantainercraft.micro.chats.convertor;

import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ChatSecuredDTOConvertor implements ConvertorDTO<ChatSecuredDTO, Chat_Secured> {
    private final ModelMapper mapper;

    public ChatSecuredDTOConvertor(@Qualifier("chat-secured") ModelMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public ChatSecuredDTO convertEntityToDTO(Chat_Secured chatSecured) {
        return mapper.map(chatSecured,ChatSecuredDTO.class);
    }

    @Override
    public Chat_Secured convertDTOToEntity(ChatSecuredDTO dto) {
        return mapper.map(dto,Chat_Secured.class);
    }
}
