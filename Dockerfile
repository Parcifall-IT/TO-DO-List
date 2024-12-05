# Используем базовый образ с JDK (например, OpenJDK 17)
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем .jar файл в контейнер (замените имя файла на своё)
COPY target/NauJava-0.0.1-SNAPSHOT.jar /app/app.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/naudb
ENV SPRING_DATASOURCE_USERNAME=operator
ENV SPRING_DATASOURCE_PASSWORD=operator
# Устанавливаем порт, на котором будет работать приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]