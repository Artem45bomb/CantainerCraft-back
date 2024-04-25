package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserChatService {
    public User_Chat save(UserChatDTO userChatDTO);

    public User_Chat update(UserChatDTO userChatDTO);

    public Integer deleteByUserId(Long userId, UUID chatId);

    public void deleteById(Long id);

    public Optional<User_Chat> findById(Long id);

    public List<User_Chat> findBySearch(Long id, Long userId, UUID chatId);

    public List<User_Chat> findAll();
}
