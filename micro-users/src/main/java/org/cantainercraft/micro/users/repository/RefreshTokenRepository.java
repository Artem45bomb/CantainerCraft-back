package org.cantainercraft.micro.users.repository;

import org.cantainercraft.project.entity.RefreshToken;
import org.cantainercraft.project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserId(Long userId);


    void deleteByUserUsername(String username);

}
