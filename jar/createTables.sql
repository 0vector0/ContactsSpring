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

INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, name) VALUES (2, 'ROLE_USER');