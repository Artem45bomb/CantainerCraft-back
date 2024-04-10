package ru.project.socket.chats.messaging.socket;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
public class SubmitMessage {
    private String messageText;
    private String img;
    private String author;
    private String pathMessage;
}
