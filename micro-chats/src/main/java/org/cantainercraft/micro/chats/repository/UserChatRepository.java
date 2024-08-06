package org.cantainercraft.micro.chats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserChatRepository extends JpaRepository<User_Chat,Long> {


    @Query("select user_chat from  User_Chat  user_chat where (user_chat.chat.uuid = :chatId or :chatId is null ) and" +
            "(user_chat.userId = :userId or :userId is null ) and " +
            "(user_chat.id=:id or :id is null )")
    List<User_Chat> findBySearch(Long id,Long userId,UUID chatId);

    Optional<User_Chat> findByChatUuidAndUserId(UUID chatId, Long userId);

    @Transactional
    @Modifying
    Integer deleteByUserIdAndChatUuid(Long userId,UUID chatId);
}
