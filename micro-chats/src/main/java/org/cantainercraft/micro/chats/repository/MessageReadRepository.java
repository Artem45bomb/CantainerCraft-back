package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.MessageRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageReadRepository extends JpaRepository<MessageRead, UUID> {
}
