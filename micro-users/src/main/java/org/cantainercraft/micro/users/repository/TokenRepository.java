package org.cantainercraft.micro.users.repository;

import org.cantainercraft.project.entity.users.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findByUsernameAndPassword(String username,String password);
}
