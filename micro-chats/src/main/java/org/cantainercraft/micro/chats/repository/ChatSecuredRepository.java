package org.cantainercraft.micro.chats.repository;

import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatSecuredRepository extends JpaRepository<Chat_Secured, UUID> {
    boolean existsByUserIdAndChat(Long userId, Chat chat);

    List<Chat_Secured> findByUserId(Long userId);

    void deleteByUserIdAndChat(Long userId,Chat chat);

}
