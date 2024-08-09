package org.cantainercraft.micro.chats.service;


import org.cantainercraft.micro.chats.repository.dto.PrivilegeDTO;
import org.cantainercraft.project.entity.chats.Privilege;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrivilegeService {

    Privilege save(PrivilegeDTO dto);

    Privilege update(PrivilegeDTO dto);

    void deleteById(UUID uuid);

    List<Privilege> findAll();

    Optional<Privilege> findById(UUID uuid);

    List<Privilege> findByChat(UUID chatId);
}