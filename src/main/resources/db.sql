CREATE TABLE USERS (
  id MEDIUMINT AUTO_INCREMENT NOT NULL ,
  login VARCHAR(20),
  password VARCHAR(15),
  name VARCHAR(15),
  surname VARCHAR(20),
  patronymic VARCHAR(20),
  isadmin BIT,
  isdoctor BIT,
  isbanned BIT,
  PRIMARY KEY (id)
);
INSERT INTO USERS (login, password, name, surname, patronymic, isadmin, isdoctor, isbanned) VALUES
  ('paver','323','Pavel','Sinelnikov','Michailovich',1,0,0);


INSERT INTO USERS (login, password, name, surname, patronymic, isadmin, isdoctor, isbanned) VALUES
  ('pavel','3455','Pavel','Sinelnikov','Michailovich',1,0,0),
  ('miha','3522234','Mihail','Sinelnikov','Petrovich',1,0,0),
  ('lilu','34542345','Tatsiana','Sinelnikova','Vasilevna',0,1,0),
  ('bab','345535','Valentina','Savchenko','Ilynichna',1,0,0),
  ('tat','345543A','Tatsiana','Sinelnikova','Ivanovna',1,0,0),
  ('max','3455as','Максим','Синельников','Михайлович',0,0,1),
  ('vasya','34da55','Василий','Савченко','Митрофанович',0,0,0);

SELECT * FROM USERS WHERE login = 'pavel';







CREATE TABLE ADMINS ( userID MEDIUMINT,FOREIGN KEY (userID) REFERENCES USERS(id), position varchar(20));



DROP TABLE USERS;
CREATE TABLE S ( p varchar(20), PRIMARY KEY(p), name text, status integer, city text);
CREATE TABLE P ( d varchar(20), PRIMARY KEY(d), name text, color text, size integer, city text);
CREATE TABLE J ( pr varchar(20), PRIMARY KEY(pr), name text,  city text);
CREATE TABLE K ( p varchar(20), FOREIGN KEY (p) REFERENCES S(p), d varchar(20), FOREIGN KEY (d) REFERENCES P(d), pr varchar(20),
  FOREIGN KEY (pr) REFERENCES J(pr), s integer);
INSERT INTO S VALUES ('П1','Петров',20,'Москва'),
  ('П2','Синицин',10,'Таллинн'),
  ('П3','Федоров',30,'Таллинн'),
  ('П4','Чаянов',20,'Минск'),
  ('П5','Крюков',30,'Киев');
INSERT INTO P VALUES ('Д1','Болт','Красный',12,'Москва'),
  ('Д2','Гайка','Зеленая',17,'Минск'),
  ('Д3','Диск','Черный',17,'Вильнюс'),
  ('Д4','Диск','Черный',14,'Москва'),
  ('Д5','Корпус','Красный',12,'Минск'),
  ('Д6','Крышки','Красный',19,'Москва');
INSERT INTO J VALUES ('ПР1','ИПР1','Минск'),
  ('ПР2','ИПР2','Таллинн'),
  ('ПР3','ИПР3','Псков'),
  ('ПР4','ИПР4','Псков'),
  ('ПР5','ИПР4','Москва'),
  ('ПР6','ИПР6','Саратов'),
  ('ПР7','ИПР7','Москва');
INSERT INTO K VALUES ('П1','Д1','ПР1',200),
  ('П1','Д1','ПР2',700),
  ('П2','Д3','ПР1',400),
  ('П2','Д2','ПР2',200),
  ('П2','Д3','ПР3',200),
  ('П2','Д3','ПР4',500),
  ('П2','Д3','ПР5',600),
  ('П2','Д3','ПР6',400),
  ('П2','Д3','ПР7',800),
  ('П2','Д5','ПР2',100),
  ('П3','Д3','ПР1',200),
  ('П3','Д4','ПР2',500),
  ('П4','Д6','ПР3',300),
  ('П4','Д6','ПР7',300),
  ('П5','Д2','ПР2',200),
  ('П5','Д2','ПР4',100),
  ('П5','Д5','ПР5',500),
  ('П5','Д5','ПР7',100),
  ('П5','Д6','ПР2',200),
  ('П5','Д1','ПР2',100),
  ('П5','Д3','ПР4',200),
  ('П5','Д4','ПР4',800),
  ('П5','Д5','ПР4',400),
  ('П5','Д6','ПР4',500);
