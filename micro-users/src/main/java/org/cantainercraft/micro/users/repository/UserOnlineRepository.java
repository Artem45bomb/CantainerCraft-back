package org.cantainercraft.micro.users.repository;

import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserOnlineRepository extends JpaRepository<User_Online,UUID>{

    @Transactional
    void deleteByUuid(UUID uuid);

    boolean existsByUserId(Long id);
}
