package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.dto.ChatUpdateDTO;
import org.cantainercraft.project.entity.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatService {

    Chat save(ChatDTO chatDTO);

    List<Chat> findBySearch(UUID uuid, String name, TypeChat typeChat, Date dateStart, Date dateEnd);

    boolean deleteByName(String name);

    public Optional<Chat> findByName(String chatName);

    Optional<Chat> findByUUID(UUID uuid);

    List<Chat> findAll();

    boolean update(ChatUpdateDTO chatUpdateDTO);

    boolean delete(UUID uuid);
}
