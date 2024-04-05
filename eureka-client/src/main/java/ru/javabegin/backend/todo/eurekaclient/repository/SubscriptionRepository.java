package ru.javabegin.backend.todo.eurekaclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javabegin.backend.todo.eurekaclient.entity.Subscription;

import java.util.Optional;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
}
