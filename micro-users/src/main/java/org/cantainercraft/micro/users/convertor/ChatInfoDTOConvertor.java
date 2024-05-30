package org.cantainercraft.micro.users.convertor;

import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.cantainercraft.project.entity.users.Profile;
import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatInfoDTOConvertor {

    private final ModelMapper modelMapper;

    public ChatInfoDTO convertChatInfoToChatInfoDTO(Chat_Info chatInfo){

        return modelMapper.map(chatInfo, ChatInfoDTO.class);

    }

    public Chat_Info convertChatInfoDTOToChatInfo(ChatInfoDTO ChatInfoDTO){
        return modelMapper.map(ChatInfoDTO,Chat_Info.class);
    }

}
