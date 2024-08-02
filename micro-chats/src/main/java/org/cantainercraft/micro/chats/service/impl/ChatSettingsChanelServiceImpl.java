package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ChatSettingsChanelDTOConvertor;
import org.cantainercraft.micro.chats.dto.ChatSettingsChanelDTO;
import org.cantainercraft.micro.chats.repository.ChatSettingsChanelRepository;
import org.cantainercraft.micro.chats.service.ChatSettingsChanelService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatSettingsChanelServiceImpl implements ChatSettingsChanelService {

    private final ChatSettingsChanelRepository repository;
    private final ChatSettingsChanelDTOConvertor convertor;

    public void delete(UUID uuid) {
        if(!repository.existsById(uuid)) {
            throw new NotResourceException("no settingsChanel by id");
        }
        repository.deleteById(uuid);
    }

    public Chat_Settings_Chanel save(ChatSettingsChanelDTO dto) {
        Chat_Settings_Chanel chatSettingsChanel = convertor.convertDTOToEntity(dto);
        return repository.save(chatSettingsChanel);
    }

    public Chat_Settings_Chanel update(ChatSettingsChanelDTO dto) {
        if(!repository.existsById(dto.getUuid())) {
            throw new NotResourceException("no settingsChanel by id");
        }
        Chat_Settings_Chanel chatSettingsChanel = convertor.convertDTOToEntity(dto);
        return repository.save(chatSettingsChanel);
    }

    public Optional<Chat_Settings_Chanel> findByUUID(UUID uuid) {
        return repository.findById(uuid);
    }
}
