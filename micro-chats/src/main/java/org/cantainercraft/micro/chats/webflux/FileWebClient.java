package org.cantainercraft.micro.chats.webflux;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.project.entity.users.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

/**
 * Компонент для взаимодействия с удаленным микросервисом пользователей через WebClient.
 */
@Component
@RequiredArgsConstructor
public class FileWebClient {

    // Поля
    @NonNull @Qualifier("fileClient") private final WebClient webClient; // Экземпляр WebClient для выполнения HTTP запросов

    public ContentDTO findBySrc(String src) {
        return webClient
                .get()
                .uri("/api/contents/file"+src)
                .retrieve()
                .bodyToMono(ContentDTO.class)
                .block();
    }
}
