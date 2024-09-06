package org.cantainercraft.micro.users.repository;

import org.cantainercraft.project.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.cantainercraft.project.entity.users.Profile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByUserIdOrUserEmail(Long id,String email);

    void deleteByUserIdOrUserEmail(Long id,String email);

    boolean existsByUser(User user);

    boolean existsByUserIdOrUserEmail(Long userId,String email);
}
