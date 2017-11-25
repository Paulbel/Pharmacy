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
  email VARCHAR(30),
  PRIMARY KEY (id)
);
SELECT name,surname,patronymic,password,login ,role,is_banned,pnone FROM users WHERE login='pavel';


INSERT INTO USERS (login, password, name, surname, patronymic, is_banned, role, pnone,email) VALUES
  ('pavel','3455','Pavel','Sinelnikov','Michailovich',1,'admin','3252525','asd@gmail.com'),
  ('miha','3522234','Mihail','Sinelnikov','Petrovich',-1,'admin','25345','asd@gmail.com'),
  ('lilu','34542345','Tatsiana','Sinelnikova','Vasilevna',-1,'doctor','234242','asd@gmail.com'),
  ('bab','345535','Valentina','Savchenko','Ilynichna',-1,'client','23432','asd@gmail.com'),
  ('tat','345543A','Tatsiana','Sinelnikova','Ivanovna',0,'client','23424','asd@gmail.com'),
  ('max','3455as','Максим','Синельников','Михайлович',-1,'client','32526','asd@gmail.com'),
  ('vasya','34da55','Василий','Савченко','Митрофанович',0,'client','62623','asd@gmail.com');

SELECT * FROM USERS WHERE login = 'pavel';



SELECT COUNT(*) FROM USERS WHERE login='miha';

DROP TABLE USERS;

