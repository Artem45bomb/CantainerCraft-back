package org.cantainercraft.micro.chats.webflux;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.project.entity.users.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Компонент для взаимодействия с удаленным микросервисом пользователей через WebClient.
 */
@Component
@RequiredArgsConstructor
public class UserWebClient {

    // Поля
    private final WebClient webClient; // Экземпляр WebClient для выполнения HTTP запросов
    @Value("${service.key}")
    private String serviceKey; // Секретный ключ для аутентификации микросервиса

    /**
     * Загружает информацию о пользователе по его имени пользователя.
     *
     * @param username имя пользователя, для которого нужно загрузить информацию
     * @return объект User с информацией о пользователе
     */
    public User loadedUser(String username) {
        return webClient
                .post() // Создание POST запроса
                .uri("/user/loadedUser") // Указание URI эндпоинта
                .bodyValue(username) // Установка тела запроса (имя пользователя)
                .header("micro-service-key", serviceKey) // Установка заголовка с секретным ключом
                .retrieve() // Отправка запроса и получение ответа
                .bodyToMono(User.class) // Преобразование тела ответа в объект User
                .block(); // Блокировка для ожидания завершения Mono и получения результата
    }

    /**
     * Проверяет существование пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return true, если пользователь существует, иначе false
     */
    public Boolean userExist(Long userId) {
        return webClient
                .post() // Создание POST запроса
                .uri("/user/exist/id") // Указание URI эндпоинта
                .bodyValue(userId) // Установка тела запроса (идентификатор пользователя)
                .header("micro-service-key", serviceKey) // Установка заголовка с секретным ключом
                .retrieve() // Отправка запроса и получение ответа
                .bodyToMono(Boolean.class) // Преобразование тела ответа в объект Boolean
                .block(); // Блокировка для ожидания завершения Mono и получения результата
    }
}