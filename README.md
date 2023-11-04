## Инструкция по запуску проекта в Docker

- Выполните команду 'mvn install' в корневой директории соответствующего сервиса для base-package;
- Выполните команду 'mvn package' в корневых директориях соответствующих сервисов  для user-service, book-service, library-service;
- Выполните команду 'docker-compose up' для сборки проекта в docker;
- Для просмотра урлов доступен open api file по адресу http://loccalhost:81
- Для доступа к базе данных доступен PgAdmin по адресу http://localhost:82
    - Логин: admin@admin.com
    - Пароль: root
    - Пароль к серверам: root
- Для отправки запросов в Postman:
    - коллекции для данного проекта можно найти в папке postman-collections корневой директории проекта