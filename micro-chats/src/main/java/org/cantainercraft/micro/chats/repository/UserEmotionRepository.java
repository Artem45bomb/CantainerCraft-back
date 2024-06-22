package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.User_Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEmotionRepository extends JpaRepository<User_Emotion, UUID> {
}
