package org.cantainercraft.micro.chats.service;

/**
 * Этот интерфейс определяет общий контракт для сервисов,
 * которые должны предоставлять функциональность инициализации объектов.
 *
 * @param <T> тип объекта, который будет инициализироваться
 */
public interface InitService<T> {

    /**
     * Инициализирует переданный объект.
     *
     * @param object объект для инициализации
     */
    public void init(T object);
}
