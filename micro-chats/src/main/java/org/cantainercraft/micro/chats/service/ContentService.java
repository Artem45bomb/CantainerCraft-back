package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.project.entity.chats.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentService {

    Content save(ContentDTO contentDTO);

    boolean update(ContentDTO contentDTO);

    boolean delete(UUID uuid);

    List<Content> findAll();

    Optional<Content> findById(UUID uuid);
}
