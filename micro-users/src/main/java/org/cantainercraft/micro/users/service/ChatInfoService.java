package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.project.entity.users.Chat_Info;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatInfoService {

    Chat_Info save (ChatInfoDTO chatInfoDTO);

    Chat_Info update(ChatInfoDTO ChatInfoDTO);

    Optional<Chat_Info> findById(UUID uuid);

    Optional<Chat_Info> findByChatId(Long chatId);

    List<Chat_Info> findAll();

    void deleteById(UUID uuid);

    void deleteByChatId(Long chatId);

    void deleteByUser(Long userId,String email);


}
