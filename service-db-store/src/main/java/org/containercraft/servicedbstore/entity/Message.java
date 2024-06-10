package org.containercraft.servicedbstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Table(name = "messages", schema = "messenger_chats")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    private UUID uuid;
    private String text;
    @Column("is_pinned")
    private Boolean isPinned;
    private TypeChat type;
    private LocalDate date;
    private Long userId;
    private UUID clientId;
    private UUID chatId;
}
