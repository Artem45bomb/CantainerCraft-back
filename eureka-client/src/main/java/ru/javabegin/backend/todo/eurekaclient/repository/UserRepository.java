package ru.javabegin.backend.todo.eurekaclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.javabegin.backend.todo.eurekaclient.entity.Subscription;
import ru.javabegin.backend.todo.eurekaclient.entity.User;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
