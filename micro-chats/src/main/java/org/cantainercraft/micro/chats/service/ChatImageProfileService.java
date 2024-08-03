package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatImageProfileService {

    Chat_Image_Profile save(ChatImageProfileDTO chatImageProfileDTO);

    boolean update(ChatImageProfileDTO chatImageProfileDTO);

    boolean delete(UUID uuid);

    List<Chat_Image_Profile> findAll();

    Optional<Chat_Image_Profile> findById(UUID uuid);

    boolean existById(UUID uuid);
}