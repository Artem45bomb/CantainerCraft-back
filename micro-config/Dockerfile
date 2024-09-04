# Используем официальный образ OpenJDK на базе Alpine Linux
FROM openjdk:17-alpine
# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAR_NAME=micro-config-0.0.1-SNAPSHOT.jar
# Копируем Gradle build файл и папку с исходниками
COPY build/libs/micro-config-0.0.1-SNAPSHOT.jar app.jar
# Указываем команду запуска для приложения
ENTRYPOINT ["java", "-jar", "app.jar"]