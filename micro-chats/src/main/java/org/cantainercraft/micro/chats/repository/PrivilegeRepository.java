package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
    List<Privilege> findPrivilegeByChatUuidOrChatName(UUID uuid, String name);

    Boolean findByChatUuidAndNameRole(UUID chatId,String role);
}
