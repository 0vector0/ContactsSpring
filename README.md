# Тестовое задание “Телефонная книга”

В проекте используются 
- Spring Boot, Spring MVC, Spring Data, Spring Security
- Thymeleaf
- JUnit
- Mockito 
- Lombok 
- Maven

В качестве базы данных по умолчанию используется **PostgreSQL**, но при помощи конфигурационного файла можно сменить на **MySQL**

Пример конфигурационного файла

```xml
spring.jpa.database=MYSQL
spring.datasource.url=jdbc:mysql://localhost/contacts
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

Так же необходимо предварительно создать таблицы

```mysql
CREATE TABLE users
(
  id        BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(255)           NOT NULL,
  password  VARCHAR(255)           NOT NULL,
  username  VARCHAR(255)           NOT NULL
);
CREATE UNIQUE INDEX UK_r43af9ap4edm43mmtq01oddj6
  ON users (username);

CREATE TABLE roles
(
  id   BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(255)
);

CREATE TABLE user_role
(
  user_id BIGINT(20) NOT NULL,
  role_id BIGINT(20) NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, role_id),
  CONSTRAINT FKj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES users (id),
  CONSTRAINT FKt7e7djp752sqn6w22i6ocqy6q FOREIGN KEY (role_id) REFERENCES roles (id)
);
CREATE INDEX FKt7e7djp752sqn6w22i6ocqy6q
  ON user_role (role_id);

CREATE TABLE contacts
(
  contact_id   BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  address      VARCHAR(255),
  email        VARCHAR(255),
  home_phone   VARCHAR(255),
  mobile_phone VARCHAR(255)           NOT NULL,
  name         VARCHAR(255)           NOT NULL,
  patronymic   VARCHAR(255)           NOT NULL,
  surname      VARCHAR(255)           NOT NULL,
  user_id      BIGINT(20)             NOT NULL,
  CONSTRAINT FKna8bddygr3l3kq1imghgcskt8 FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE INDEX FKna8bddygr3l3kq1imghgcskt8
  ON contacts (user_id);
```

И заполнить таблицу ролей

```mysql
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');
```

**.jar** файл проекта лежит в папке /jar (Там же лежат sql-скрипт и конфигурационный файл)

Для запуска необходимо использовать команду

```
java -jar contacts-0.0.1-SNAPSHOT.jar --spring.config.location=config.properties
```

Приложение залито на **Heroku** и демо доступ доступен по ссылке [https://lardicontacts.herokuapp.com/](https://lardicontacts.herokuapp.com/ "https://lardicontacts.herokuapp.com/").

```
login : username
password : password
```

Полный текст задания доступен в файле [TODO.MD](https://github.com/0vector0/LardiContacts/blob/master/TODO.md "TODO.MD").




