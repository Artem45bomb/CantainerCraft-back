package org.cantainercraft.micro.chats.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.cantainercraft.micro.chats.service.MessageServiceAOP;
import org.cantainercraft.project.entity.chats.Message;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Aspect
@RequiredArgsConstructor
@Component
public class MessageAOP {
    private final MessageServiceAOP messageService;

//    @AfterReturning(value = "execution(* org.cantainercraft.micro.chats.service.MessageService.save(*))",returning = "object")
//    public void addMessage(Object object){
//        if(object instanceof Message message) {
//            messageService.addMessage(message);
//        }
//    }
//
//    @AfterReturning(value = "execution(* org.cantainercraft.micro.chats.service.MessageService.update(*))",returning = "object")
//    public void updateMessage(Object object){
//        if(object instanceof Message message) {
//            messageService.updateMessage(message);
//        }
//    }
//
//
//    @Before(value = "execution(* org.cantainercraft.micro.chats.service.MessageService.delete(*)) && args(uuid)")
//    public void deleteMessage(UUID uuid){
//        messageService.deleteMessage(uuid);
//    }
}
