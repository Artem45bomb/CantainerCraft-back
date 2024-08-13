package org.cantainercraft.micro.chats.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.cantainercraft.micro.chats.repository.dto.ChatSettingsDTO;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Settings_Chanel;
import org.cantainercraft.project.entity.chats.Chat_Settings_Group;
import org.cantainercraft.project.entity.users.TypeChat;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class ChatAOP {
    private final ChatSettingsService chatSettingsService;
    private final ChatService chatService;

    @AfterReturning(value = "execution(* org.cantainercraft.micro.chats.service.ChatService.save(*))",returning = "chat")
    public void initSettingsForChat(Chat chat){
        Optional<Chat> chatFind = chatService.findByUUID(chat.getUuid());

        chatFind.ifPresent(e -> {
                    ChatSettingsDTO settings = new ChatSettingsDTO();
                    settings.setChat(e);
                    if (e.getTypeChat().equals(TypeChat.GROUP)) {
                        settings.setChatSettingsGroup(Chat_Settings_Group.builder().build());
                    }
                    if (e.getTypeChat().equals(TypeChat.PERSONALLY)) {
                        settings.setChatSettingsChanel(Chat_Settings_Chanel.builder().build());
                    }

                    chatSettingsService.save(settings);
                }
        );
    }
}
