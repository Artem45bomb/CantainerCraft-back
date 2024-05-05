package org.cantainercraft.micro.chats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.cantainercraft.project.entity.chats.Message;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByUserId(Long userId);

    @Query("select message from  Message message where( lower(message.value) like lower(concat('%',:value,'%')) or :value is null or :value = '') and" +
            "(message.uuid = :uuid or :uuid is null) and" +
            "(cast(:dateStart as timestamp ) is null or message.date>=:dateStart ) and" +
            "(cast(:dateEnd as timestamp ) is null or  message.date <=:dateEnd)"+
            "and (:userId is null or message.userId=:userId) ")
    List<Message> findBySearch(@Param("dateStart") Date dateStart,
                               @Param("dateEnd") Date dateEnd,
                               @Param("value")String value,
                               @Param("uuid") UUID uuid,
                               @Param("userId") Long userId);
}
