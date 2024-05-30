package org.cantainercraft.micro.users.repository;

import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOnlineRepository extends JpaRepository<User_Online, Boolean>{

    Optional<User_Online> findById(int id);

    void deleteById(int id);

    boolean existsByUserId(Long id);
}
