package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsChanelDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.chats.dto.ChatSettingsChanelUpdateDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsChanelRopository;
import org.cantainercraft.micro.chats.service.ChatSettingsChanelService;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatSettingsChanelServiceImpl  implements ChatSettingsChanelService {

    private final ChatSettingsChanelRopository chatSettingsChanelRopository;
    private final ChatSettingsChanelDTOConvertor chatSettingsChanelDTOConvertor;

    public boolean delete(UUID uuid) {
        return chatSettingsChanelRopository.findById(uuid).isPresent();
    }

    public Chat_Settings_Chanel save(ChatSettingsChanelDTO chatSettingsChanelDTO) {
        Chat_Settings_Chanel chatSettingsChanel = chatSettingsChanelDTOConvertor.convertChatSettingsChanelDTOToChatSettingsChanel(chatSettingsChanelDTO);
        return chatSettingsChanelRopository.save(chatSettingsChanel);
    }

    public boolean update(ChatSettingsChanelUpdateDTO chatSettingsChanelUpdateDTO) {
        Chat_Settings_Chanel chatSettingsChanel = chatSettingsChanelDTOConvertor.convertChatSettingsChanelDTOToChatSettingsChanel(chatSettingsChanelUpdateDTO);
        chatSettingsChanel.setUuid(chatSettingsChanelUpdateDTO.getUuid());
        chatSettingsChanelRopository.save(chatSettingsChanel);
        return true;
    }

    public Optional<Chat_Settings_Chanel> findByUUID(UUID uuid) {
        return chatSettingsChanelRopository.findById(uuid);
    }

    public List<Chat_Settings_Chanel> findByChatId(UUID uuid) {
        return chatSettingsChanelRopository.findByChatId(uuid);
    }
}
