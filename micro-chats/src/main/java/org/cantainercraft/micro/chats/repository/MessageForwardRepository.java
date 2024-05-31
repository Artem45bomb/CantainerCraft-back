package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Message_Forward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageForwardRepository extends JpaRepository<Message_Forward, UUID> {
}
