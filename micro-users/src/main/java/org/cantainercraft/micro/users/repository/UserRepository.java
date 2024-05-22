package org.cantainercraft.micro.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.cantainercraft.project.entity.users.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    @Modifying
    void  deleteByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username,String password);


    User getByUsername(String username);

    boolean existsById(Long id);

    boolean existsByUsername(String name);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query("select user from User user where (:email is null or lower(user.email) like lower(concat('%',:email,'%'))) and" +
            "(:password is null or lower(user.password) like lower(concat('%',:password,'%')))")
    List<User> findBySearch(@Param("email") String email,
                            @Param("password") String password);
}
