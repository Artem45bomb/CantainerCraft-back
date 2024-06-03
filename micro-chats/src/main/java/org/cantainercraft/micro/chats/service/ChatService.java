package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatService {

     Chat save(ChatDTO chatDTO);

     boolean update(ChatDTO chatUpdateDTO);

     boolean delete(UUID uuid);

     boolean deleteByName(String name);


    List<Chat> findBySearch(UUID uuid, String name, TypeChat typeChat, Date dateStart, Date dateEnd);

    Optional<Chat> findByName(String chatName);

    List<Chat> findAll();

    Optional<Chat> findByUUID(UUID uuid);
}
