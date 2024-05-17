package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, UUID> {

    Optional<Emotion> findByUnicode(String unicode);

    void deleteByUnicode(String unicode);
}
