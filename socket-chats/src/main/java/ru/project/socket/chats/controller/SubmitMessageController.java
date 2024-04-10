package ru.project.socket.chats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.project.socket.chats.messaging.socket.SubmitMessage;

@Controller
@Slf4j
public class SubmitMessageController {
    @MessageMapping("/submit")
    @SendTo("/topic/public")
    private String submitMessage(@Payload SubmitMessage submitMessage) throws Exception{
        log.info(submitMessage.getAuthor()+":"+submitMessage.getMessageText());
        return submitMessage.getAuthor()+":"+submitMessage.getMessageText();
    }
}
