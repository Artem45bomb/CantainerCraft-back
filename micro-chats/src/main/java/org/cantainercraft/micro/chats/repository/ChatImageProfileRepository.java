package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat_Image_Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatImageProfileRepository extends JpaRepository<Chat_Image_Profile, UUID> {
}