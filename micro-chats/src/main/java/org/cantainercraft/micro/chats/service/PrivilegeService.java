package org.cantainercraft.micro.chats.service;


import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.project.entity.chats.Privilege;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrivilegeService {

    Privilege save(PrivilegeDTO privilegeDTO);

    boolean update(PrivilegeDTO privilegeDTO);

    boolean delete(UUID uuid);

    List<Privilege> findAll();

    Optional<Privilege> findById(UUID uuid);

    List<Privilege> findByChat(UUID uuid, String name);
}