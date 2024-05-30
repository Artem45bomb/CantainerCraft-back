package org.cantainercraft.micro.users.service.impl;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.ChatInfoDTOConvertor;
import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.repository.ChatInfoRepository;
import org.cantainercraft.micro.users.service.ChatInfoService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatInfoServiceImpl implements ChatInfoService {
    private final ChatInfoRepository chatInfoRepository;
    private final ChatInfoDTOConvertor chatInfoDTOConvertor;

    @Override
    public Chat_Info save(ChatInfoDTO chatInfoDTO) {
        if (chatInfoRepository.existsById(chatInfoDTO.getUuid())) {
            throw new NotResourceException("ChatInfo already exists");
        }
        else {
        Chat_Info chatInfo = chatInfoDTOConvertor.convertChatInfoDTOToChatInfo(chatInfoDTO);
        return chatInfoRepository.save(chatInfo);
        }
    }


    @Override
    public Chat_Info update(ChatInfoDTO chatInfoDTO) {
        Chat_Info chatInfo = chatInfoDTOConvertor.convertChatInfoDTOToChatInfo(chatInfoDTO);
        chatInfo.setUuid(chatInfoDTO.getUuid());
        chatInfoRepository.save(chatInfo);
        return chatInfoRepository.save(chatInfo);
    }

    @Override
    public Optional<Chat_Info> findById(UUID uuid) {
        if (chatInfoRepository.existsById(uuid)){
            throw new NotResourceException("UUID not Found.");
        }
        else{
            return chatInfoRepository.findById(uuid);
        }
    }

    @Override
    public Optional<Chat_Info> findByChatId(Long chatId) {

        return Optional.empty();
    }

    @Override
    public List<Chat_Info> findAll() {

        return chatInfoRepository.findAll();
    }

    @Override
    public void deleteById(UUID uuid) {
        if (chatInfoRepository.existsById(uuid)) {
            throw new NotResourceException("UUID not Found.");
        }
        else{
            chatInfoRepository.deleteById(uuid);
        }
    }

    @Override
    public void deleteByChatId(Long chatId) {

    }

    @Override
    public void deleteByUser(Long userId, String email) {
    }
}
