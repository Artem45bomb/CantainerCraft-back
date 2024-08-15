package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserChatService {

    User_Chat save(UserChatDTO dto);

    User_Chat update(UserChatDTO dto);

    void deleteByUserId(Long userId, UUID chatId);

    void deleteById(Long id);

    Optional<User_Chat> findById(Long id);

    List<User_Chat> findBySearch(Long userId, UUID chatId);

    List<User_Chat> findAll();
}
