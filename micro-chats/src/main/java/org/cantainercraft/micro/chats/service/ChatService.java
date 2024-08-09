package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.repository.dto.ChatDTO;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatService {

     Chat save(ChatDTO chatDTO);

     Chat update(ChatDTO chatUpdateDTO);

     void delete(UUID uuid);

    List<Chat> findBySearch(UUID uuid, String name, TypeChat typeChat, Date dateStart, Date dateEnd);

    List<Chat> findAll();

    Optional<Chat> findByUUID(UUID uuid);
}
