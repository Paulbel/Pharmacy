CREATE TABLE user (
  id         MEDIUMINT AUTO_INCREMENT NOT NULL,
  login      VARCHAR(20),
  password   VARCHAR(16),
  name       VARCHAR(15),
  surname    VARCHAR(20),
  patronymic VARCHAR(20),
  is_banned  BIT,
  PRIMARY KEY (id)
);

CREATE TABLE client (
  user_id MEDIUMINT,
  FOREIGN KEY (user_id) REFERENCES user (id),
  pnone   VARCHAR(20),
  email   VARCHAR(30)
);
SELECT name,surname,patronymic,password,login ,is_banned FROM user;



SELECT id,name,surname,patronymic,password,login ,is_banned FROM user WHERE login='pavel';

CREATE TABLE doctor (
  user_id MEDIUMINT,
  category ENUM ('Вторая', 'Первая', 'Высшая'),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE admin (
  user_id MEDIUMINT,
  FOREIGN KEY (user_id) REFERENCES user (id),
  rank ENUM ('Главный', 'Новичок', 'Обычный')
);

CREATE TABLE visit (
  doctor_id MEDIUMINT,
  FOREIGN KEY (doctor_id) REFERENCES user (id),
  client_id MEDIUMINT,
  FOREIGN KEY (doctor_id) REFERENCES user (id)

);


INSERT INTO user (login, password, name, surname, patronymic, is_banned) VALUES
  ('pavel', '3455', 'Pavel', 'Sinelnikov', 'Michailovich', 1),
  ('miha', '3522234', 'Mihail', 'Sinelnikov', 'Petrovich', 0),
  ('lilu', '34542345', 'Tatsiana', 'Sinelnikova', 'Vasilevna', 1),
  ('bab', '345535', 'Valentina', 'Savchenko', 'Ilynichna', 0),
  ('tat', '345543A', 'Tatsiana', 'Sinelnikova', 'Ivanovna', 0),
  ('max', '3455as', 'Максим', 'Синельников', 'Михайлович', 1),
  ('vasya', '34da55', 'Василий', 'Савченко', 'Митрофанович', 0);

INSERT INTO admin (user_id, rank) VALUES
  (8, 'Главный'),
  (9, 'Обычный'),
  (10, 'Обычный');


SELECT *
FROM USERS
WHERE login = 'pavel';


SELECT COUNT(*)
FROM USERS
WHERE login = 'miha';

DROP TABLE users;

CREATE TABLE S (
  p      VARCHAR(20),
  PRIMARY KEY (p),
  name   TEXT,
  status INTEGER,
  city   TEXT
);
CREATE TABLE P (
  d     VARCHAR(20),
  PRIMARY KEY (d),
  name  TEXT,
  color TEXT,
  size  INTEGER,
  city  TEXT
);
CREATE TABLE J (
  pr   VARCHAR(20),
  PRIMARY KEY (pr),
  name TEXT,
  city TEXT
);
CREATE TABLE K (
  p  VARCHAR(20),
  FOREIGN KEY (p) REFERENCES S (p),
  d  VARCHAR(20),
  FOREIGN KEY (d) REFERENCES P (d),
  pr VARCHAR(20),
  FOREIGN KEY (pr) REFERENCES J (pr),
  s  INTEGER
);
INSERT INTO S VALUES ('П1', 'Петров', 20, 'Москва'),
  ('П2', 'Синицин', 10, 'Таллинн'),
  ('П3', 'Федоров', 30, 'Таллинн'),
  ('П4', 'Чаянов', 20, 'Минск'),
  ('П5', 'Крюков', 30, 'Киев');
INSERT INTO P VALUES ('Д1', 'Болт', 'Красный', 12, 'Москва'),
  ('Д2', 'Гайка', 'Зеленая', 17, 'Минск'),
  ('Д3', 'Диск', 'Черный', 17, 'Вильнюс'),
  ('Д4', 'Диск', 'Черный', 14, 'Москва'),
  ('Д5', 'Корпус', 'Красный', 12, 'Минск'),
  ('Д6', 'Крышки', 'Красный', 19, 'Москва');
INSERT INTO J VALUES ('ПР1', 'ИПР1', 'Минск'),
  ('ПР2', 'ИПР2', 'Таллинн'),
  ('ПР3', 'ИПР3', 'Псков'),
  ('ПР4', 'ИПР4', 'Псков'),
  ('ПР5', 'ИПР4', 'Москва'),
  ('ПР6', 'ИПР6', 'Саратов'),
  ('ПР7', 'ИПР7', 'Москва');
INSERT INTO K VALUES ('П1', 'Д1', 'ПР1', 200),
  ('П1', 'Д1', 'ПР2', 700),
  ('П2', 'Д3', 'ПР1', 400),
  ('П2', 'Д2', 'ПР2', 200),
  ('П2', 'Д3', 'ПР3', 200),
  ('П2', 'Д3', 'ПР4', 500),
  ('П2', 'Д3', 'ПР5', 600),
  ('П2', 'Д3', 'ПР6', 400),
  ('П2', 'Д3', 'ПР7', 800),
  ('П2', 'Д5', 'ПР2', 100),
  ('П3', 'Д3', 'ПР1', 200),
  ('П3', 'Д4', 'ПР2', 500),
  ('П4', 'Д6', 'ПР3', 300),
  ('П4', 'Д6', 'ПР7', 300),
  ('П5', 'Д2', 'ПР2', 200),
  ('П5', 'Д2', 'ПР4', 100),
  ('П5', 'Д5', 'ПР5', 500),
  ('П5', 'Д5', 'ПР7', 100),
  ('П5', 'Д6', 'ПР2', 200),
  ('П5', 'Д1', 'ПР2', 100),
  ('П5', 'Д3', 'ПР4', 200),
  ('П5', 'Д4', 'ПР4', 800),
  ('П5', 'Д5', 'ПР4', 400),
  ('П5', 'Д6', 'ПР4', 500);
