package org.cantainercraft.micro.chats.service.impl;


import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.MessageDeleteRabbitDTO;
import org.cantainercraft.micro.chats.repository.dto.MessageRabbitDTO;
import org.cantainercraft.micro.chats.service.MessageServiceAOP;
import org.cantainercraft.project.entity.chats.Message;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceAOPImpl implements MessageServiceAOP {
    private final StreamBridge streamBridge;
    @Override
    public void addMessage(Message message) {
       submitMessage(message,"add");
    }

    @Override
    public void updateMessage(Message message) {
        submitMessage(message,"update");
    }

    @Override
    public void deleteMessage(UUID uuid) {
        submitDeleteMessage(uuid,"delete");
    }

    @Override
    public void submitMessage(Message message,String actionType) {
        var messageSend = MessageRabbitDTO.builder()
                .message(message)
                .actionType(actionType)
                .build();
        streamBridge.send("submitMessage-out-0", MessageBuilder.withPayload(messageSend));
    }

    @Override
    public void submitDeleteMessage(UUID uuid, String actionType) {
        var messageSend = MessageDeleteRabbitDTO.builder()
                .uuid(uuid)
                .actionType(actionType)
                .build();
        streamBridge.send("submitMessage-out-0", MessageBuilder.withPayload(messageSend));
    }
}
