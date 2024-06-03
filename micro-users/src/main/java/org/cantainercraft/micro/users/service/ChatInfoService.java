package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.project.entity.users.Chat_Info;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatInfoService {

    Chat_Info save (ChatInfoDTO chatInfoDTO);

    Chat_Info update(ChatInfoDTO ChatInfoDTO);

    Chat_Info findById(UUID uuid);

    List<Chat_Info> findAll();

    void deleteById(UUID uuid);

}
