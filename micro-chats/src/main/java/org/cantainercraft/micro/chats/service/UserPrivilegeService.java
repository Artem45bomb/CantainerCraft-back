package org.cantainercraft.micro.chats.service;

import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.project.entity.chats.User_Privilege;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPrivilegeService {

    User_Privilege save(UserPrivilegeDTO dto);

    User_Privilege update(UserPrivilegeDTO dto);

    void delete(UUID uuid);

    List<User_Privilege> findAll();

    Optional<User_Privilege> findById(UUID uuid);

    Optional<User_Privilege> findByUserId(Long id);
}
