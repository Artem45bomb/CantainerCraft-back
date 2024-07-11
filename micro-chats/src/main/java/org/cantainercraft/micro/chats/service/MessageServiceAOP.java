package org.cantainercraft.micro.chats.service;

import org.cantainercraft.project.entity.chats.Message;

import java.util.UUID;

public interface MessageServiceAOP {
    void addMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(UUID uuid);

    void submitMessage(Message message,String actionType);

    void submitDeleteMessage(UUID uuid,String actionType);
}
