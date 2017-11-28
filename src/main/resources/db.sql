CREATE TABLE USER (
  id MEDIUMINT AUTO_INCREMENT NOT NULL ,
  login VARCHAR(20) NOT NULL ,
  password VARCHAR(15) NOT NULL ,
  name VARCHAR(15) NOT NULL ,
  surname VARCHAR(20) NOT NULL ,
  patronymic VARCHAR(20) NOT NULL ,
  is_banned TINYINT(1) DEFAULT 0 NOT NULL ,
  pnone VARCHAR(20),
  role  ENUM('DOCTOR','ADMIN','USER') NOT NULL DEFAULT 'USER',
  email VARCHAR(30),
  PRIMARY KEY (id)
);
com.mysql.jdbc.JDBC42PreparedStatement@56ef9176: SELECT name,surname,patronymic,password,login ,role,is_banned, pnone, email FROM user WHERE id=6;
SELECT name,surname,patronymic,password,login ,role,is_banned,pnone, email FROM user WHERE login='pavel';
SELECT * FROM user WHERE login='vas';
SELECT role from user where id= 1;

INSERT INTO user (login, password, name, surname, patronymic, pnone,email) VALUES

  ('miha','3522234','Mihail','Sinelnikov','Petrovich','25345','asd@gmail.com'),
  ('lilu','34542345','Tatsiana','Sinelnikova','Vasilevna','234242','asd@gmail.com'),
  ('tat','345543A','Tatsiana','Sinelnikova','Ivanovna',0,'asd@gmail.com'),
  ('max','3455as','Максим','Синельников','Михайлович',1,'asd@gmail.com'),
  ('vasya','34da55','Василий','Савченко','Митрофанович',0,'asd@gmail.com');


INSERT INTO user (login, password, name, surname, patronymic, role,  pnone,email) VALUES
  ('pavel','3455','Pavel','Sinelnikov','Michailovich','ADMIN','3252525','asd@gmail.com');

SELECT * FROM USERS WHERE login = 'pavel';



SELECT COUNT(*) FROM USERS WHERE login='miha';

DROP TABLE USER;

