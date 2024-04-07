package ru.javabegin.backend.todo.eurekaclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.weather.project.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
}
