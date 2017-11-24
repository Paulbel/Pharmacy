CREATE TABLE USERS (
  id MEDIUMINT AUTO_INCREMENT NOT NULL ,
  login VARCHAR(20),
  password VARCHAR(15),
  name VARCHAR(15),
  surname VARCHAR(20),
  patronymic VARCHAR(20),
  is_banned BIT,
  pnone VARCHAR(20),
  role ENUM('DOCTOR','ADMIN','CLIENT','USER'),
  PRIMARY KEY (id)
);
SELECT name,surname,patronymic,password,login ,role,is_banned,pnone FROM users WHERE login='pavel';


INSERT INTO USERS (login, password, name, surname, patronymic, is_banned, role, pnone) VALUES
  ('pavel','3455','Pavel','Sinelnikov','Michailovich',1,'admin','3252525'),
  ('miha','3522234','Mihail','Sinelnikov','Petrovich',0,'admin','25345'),
  ('lilu','34542345','Tatsiana','Sinelnikova','Vasilevna',1,'doctor','234242'),
  ('bab','345535','Valentina','Savchenko','Ilynichna',0,'client','23432'),
  ('tat','345543A','Tatsiana','Sinelnikova','Ivanovna',1,'client','23424'),
  ('max','3455as','Максим','Синельников','Михайлович',0,'client','32526'),
  ('vasya','34da55','Василий','Савченко','Митрофанович',1,'client','62623');

SELECT * FROM USERS WHERE login = 'pavel';



DROP TABLE USERS;

