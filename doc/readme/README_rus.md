[English](..%2F..%2FREADME.md)

# DI-object-data-extractor

---
*Один из модулей проекта "Помощник инженера "РЖД", отвечающий за получение информации об объектах инфраструктуры.
Имитирует подключение к базе данных паспортных данных объектов инфраструктуры ЕК АСУИ.*

---
![ekasui-passport.jpg](ekasui-passport.jpg)

При создании документов в подразделениях дирекции инфраструктуры часто приходится к базе паспортных данных
объектов для предоставления нужной информации. В большинстве случаев инженера знают, как правильно 
воспользоваться имеющимися сервисами и получить требующиеся сведения, однако этот процесс, как и создание 
документа в целом, занимает продолжительное время. При автоматизированном создании документов с помощью 
приложения "Помощник инженера РЖД" вышеописанный процесс упрощается за счет отправки запроса в данный сервис 
и получения нужных данных в специальных DTO. 

---
API приложения представляет собой get-эндпоинты (на данный момент 8 эндпоинтов) с указанием необходимых
параметров. Возвращает DTO с данными, касающимися объекта инфраструктуры или необходимыми для создания документа.
Microsoft Excel таблицы с используемыми вымышленными данными, их pdf-репрезентации, OpenAPI-спецификация с отражением эндпоинтов и DTO моделей 
представлены в папке [doc](..).
Используемая ER-диаграмма вымышленных сущностей базы данных имеет вид:
[diode-database-er-diagram.pdf](..%2Fdiode-database-er-diagram.pdf)

---
Версия Java - 21;

Приложение работает на фреймворке Spring Boot v. 3.3.3;

Система сборки - Apache Maven;

База данных - PostgreSQL, система миграции данных - flyway;

Обращение к БД и мэппинг сущностей - spring-boot-starter-data-jpa, hibernate;

Тестирование - JUnit, spring-boot-test, Mockito;

___

Сервис разделен на 3 рабочих и 3 вспомогательных модуля:

client - содержит spring-component(@Service) клиент, позволяющий отправить http-запрос к данному сервису и
обработать ответ для получения необходимых данных.

diodedtos - содержит модель DTO возвращаемых данных и утилитарный класс для создания тестовых объектов.

server - содержит логику получения и обработки запроса данных об объектах инфраструктуры.

tcddtos - содержит модели DTO для создания документов, относится к модулю проекта 'three-click-doc', необходим
для обработки запросов на получение исчерпывающей информации для создания документов.

ededtos - содержит модели DTO для получения расширенной информации о работниках подразделений, относится к 
модулю проекта 'employee-data-extractor' и является зависимостью модуля tcddtos.

commonclasses - модуль проекта "Помощник инженера "РЖД", содержит общие для всех модулей классы
(исключения, DTO и др.)

---

Инструкция по запуску приложения локально:
1. Для запуска приложения необходимо программное обеспечение
- Git (вариант гайда по установке - https://learn.microsoft.com/ru-ru/devops/develop/git/install-and-set-up-git);
- JDK (java SE21, вариант гайда по установке - https://blog.sf.education/ustanovka-jdk-poshagovaya-instrukciya-dlya-novichkov/);
- Apache Maven (вариант гайда по установке на Windows - https://byanr.com/installation-guides/maven-windows-11/);
- СУБД Postgresql, желательно актуальной версии.
2. Запустить терминал/командную строку/PowerShell, выполнить команды:
```
cd [целевая директория для загрузки проекта]

git clone git@github.com:RuslanYapparov/di-object-data-extractor.git

cd di-object-data-extractor/

mvn install

cd server/

mvn spring-boot:run  

```
3. Перед запуском приложения убедитесь, что порт 8070 свободен - по умолчанию на него будут приниматься
http-запросы в соответствии с API (http://localhost:8070/). В случае необходимости можно поменять соответствующую
настройку в файле [application.properties](..%2F..%2Fserver%2Fsrc%2Fmain%2Fresources%2Fapplication.properties)