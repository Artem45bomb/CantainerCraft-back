package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Message_Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageReplyRepository extends JpaRepository<Message_Reply, UUID> {

    Optional<Message_Reply> findByMessageReplyUserId(Long userId);

    void deleteByMessageReplyUserId(Long userId);
}
