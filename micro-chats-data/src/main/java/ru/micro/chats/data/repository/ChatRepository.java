package ru.micro.chats.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.weather.project.entity.chats.Chat;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

    boolean deleteByName(String name);

    Optional<Chat> findByName(String name);

//    @Query("select chat from Chat chat where (:uuid is null or :uuid = '' or chat.uuid = :uuid) and" +
//            "(:chatName is null  or chat.name = :chatName) and" +
//            "(:typeChat is null  or chat.typeChat = :typeChat) and" +
//            "(:dateStart is null or :dateEnd is null or ( chat.date between :dateStart and :dateEnd))")
//    List<Chat> findBySearch(UUID uuid, String chatName, TypeChat typeChat,Date dateStart ,Date dateEnd);
}
