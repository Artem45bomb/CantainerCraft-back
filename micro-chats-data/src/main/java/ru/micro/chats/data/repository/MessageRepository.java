package ru.micro.chats.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.weather.project.entity.chats.Message;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByUserId(Long userId);

//    @Query("select message from  Message message where  ((message.value = :value) or (:value is null or :value = ''))" +
//            "and ((message.uuid = :uuid) or(message.uuid is null)) " +
//            "and(:dateStart is null or :dateEnd is null or ( message.date between :dateStart and :dateEnd))"+
//            "and (:chatId is null or message.userId=:chatId) ")
//    List<Message> findBySearch(Date dateStart,Date dateEnd,String value,UUID uuid, Long userId);
}
