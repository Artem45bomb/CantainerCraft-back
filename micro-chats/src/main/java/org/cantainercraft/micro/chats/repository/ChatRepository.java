package org.cantainercraft.micro.chats.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    boolean existsByLink(String link);

    @Query("select chat from Chat chat where (:uuid is null or chat.uuid = :uuid) and" +
            "( :chatName is null  or lower(chat.name) like  lower(concat('%',:chatName,'%'))) and" +
            "(:typeChat is null  or chat.typeChat = :typeChat) and" +
            "(cast(:dateStart as timestamp ) is null or chat.date>=:dateStart) and" +
            "(cast(:dateEnd as timestamp ) is null  or chat.date<=:dateEnd)")
    List<Chat> findBySearch(@Param("uuid") UUID uuid,
                            @Param("chatName") String chatName,
                            @Param("typeChat") TypeChat typeChat,
                            @Param("dateStart") Date dateStart ,
                            @Param("dateEnd") Date dateEnd);
}
