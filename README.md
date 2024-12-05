---

# 📋 **ToDo List Project (Java Spring)**

Добро пожаловать в репозиторий **ToDo List** — приложение для управления задачами, разработанное с использованием **Java Spring Boot**. Оно предоставляет функционал для пользователей и администраторов, включая работу с базой данных и мониторинг системы.

---

## 🚀 **Функциональность**

### **Пользовательские возможности:**
- **Создание задач:** Легко создавайте новые задачи с описанием и сроками выполнения.
- **Редактирование задач:** Обновляйте информацию о задачах в любое время.
- **Завершение задач:** Отмечайте задачи как выполненные, чтобы организовать свои дела.

### **Административные возможности:**
- **Генерация отчетов:** Получайте отчеты о задачах и пользователях.
- **Работа с базой данных через Swagger UI:** Удобный интерфейс для тестирования и выполнения запросов к API.
- **Мониторинг системы:** Доступ к информации о состоянии системы через `/monitoring`.

---

## 🛠️ **Установка и запуск**

### **1. Клонирование репозитория**
```bash
git clone <ссылка-на-репозиторий>
cd todo-list-project
```

### **2. Установка зависимостей и сборка проекта**
Проект использует **Maven** или **Gradle**. Убедитесь, что у вас установлен JDK 17 или выше.

#### **Maven:**
```bash
mvn clean install
```

#### **Gradle:**
```bash
./gradlew build
```

### **3. Запуск приложения**
```bash
java -jar target/NauJava-0.0.1-SNAPSHOT.jar
```

---

## 📄 **API-документация**

API-документация доступна через Swagger UI:

- **URL:** `http://localhost:8080/swagger-ui`

Вы сможете:
- Просматривать доступные эндпоинты.
- Тестировать запросы (GET, POST, PUT, DELETE).
- Изучать структуру API и параметры.

---

## 📊 **Мониторинг системы**

Доступен по адресу:  
`http://localhost:8080/monitoring`  

Здесь представлена информация о производительности и состоянии сервера.

---

## 📦 **Запуск с использованием Docker**

### **1. Загрузка Docker-образа из Releases**
Перейдите в раздел [Releases](https://github.com/Parcifall-IT/TO-DO-List/releases) и скачайте последнюю версию Docker-образа.

### **2. Запуск контейнера**
```bash
docker load < путь_к_tar_файлу
docker run -d -p 8080:8080 todolist
```

- Приложение будет доступно по адресу: `http://localhost:8080`

---

## ⚙️ **Конфигурация**

Настройки приложения находятся в файле `application.yml` или `application.properties`:
- **Порт:** Настраивается через параметр `server.port`.
- **База данных:** Убедитесь, что подключение к базе данных корректно указано.

---

## 🤝 **Содействие и поддержка**

Мы приветствуем ваш вклад!  
Создавайте **issues** для обсуждения проблем или улучшений и отправляйте **pull requests**.

---

🚀 **Спасибо за использование ToDo List на Java Spring!**

---
