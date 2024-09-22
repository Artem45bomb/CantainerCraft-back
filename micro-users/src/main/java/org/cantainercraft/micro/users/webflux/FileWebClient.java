package org.cantainercraft.micro.users.webflux;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ContentDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jmx.export.annotation.ManagedNotifications;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Компонент для взаимодействия с удаленным микросервисом files через WebClient.
 */
@Component
@RequiredArgsConstructor
public class FileWebClient {

    // Поля
    @Qualifier("fileClient") private final WebClient webClient; // Экземпляр WebClient для выполнения HTTP запросов

    public ContentDTO findBySrc(String src) {
        return webClient
                .get()
                .uri("/api/contents/file"+src)
                .retrieve()
                .bodyToMono(ContentDTO.class)
                .block();
    }
}
