package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPrivilegeRepository extends JpaRepository<User_Privilege, UUID> {
    Optional<User_Privilege> findByUserId(Long id);
    void deleteByUserId(Long id);

}
