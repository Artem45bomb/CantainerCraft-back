package org.cantainercraft.micro.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.cantainercraft.project.entity.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email,String password);

    @Query("select user from User user where (:email is null or lower(user.email) like lower(concat('%',:email,'%'))) and" +
            "(:password is null or lower(user.password) like lower(concat('%',:password,'%')))")
    List<User> findBySearch(@Param("email") String email,
                            @Param("password") String password);
}
