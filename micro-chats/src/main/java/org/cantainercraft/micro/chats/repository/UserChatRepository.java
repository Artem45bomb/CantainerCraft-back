package org.cantainercraft.micro.chats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserChatRepository extends JpaRepository<User_Chat,Long> {


    @Query("select user_chat from  User_Chat  user_chat where" +
            "(user_chat.userId = :userId or :userId is null ) and " +
            "(user_chat.chat.uuid=:chatId or :chatId is null )")
    List<User_Chat> findBySearch(Long userId,UUID chatId);

    boolean existsByUserIdAndChatUuid(Long userId,UUID chatId);

    void deleteByUserIdAndChatUuid(Long userId,UUID chatUuid);
}
