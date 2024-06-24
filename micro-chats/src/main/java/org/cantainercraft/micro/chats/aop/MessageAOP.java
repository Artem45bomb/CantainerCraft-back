package org.cantainercraft.micro.chats.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.cantainercraft.project.entity.chats.Message;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@RequiredArgsConstructor
@Component
public class MessageAOP {
    private final StreamBridge streamBridge;

    @AfterReturning(value = "execution(* org.cantainercraft.micro.chats.service.MessageService.save(*))",returning = "object")
    public void submitMessage(Object object){
        if(object instanceof Message message) {
            streamBridge.send("submitMessage-out-0", MessageBuilder.withPayload(message));
        }
    }
}
