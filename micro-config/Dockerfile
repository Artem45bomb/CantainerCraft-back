# Используем официальный образ OpenJDK на базе Alpine Linux
FROM openjdk:17-alpine
# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=prod
# Копируем Gradle build файл и папку с исходниками
COPY build/libs/*.jar app.jar
# Указываем команду запуска для приложения
ENTRYPOINT ["java", "-jar", "app.jar"]