package ru.javabegin.backend.todo.eurekaclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.weather.project.entity.Profile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByUserIdOrUserEmail(Long id,String email);

    void deleteByUserIdOrUserEmail(Long id,String email);
}
