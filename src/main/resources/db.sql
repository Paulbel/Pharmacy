
INSERT INTO pharmacy.user (login, password, name, surname, patronymic, phone, email) VALUES
  ('miha', '3522234', 'Mihail', 'Sinelnikov', 'Petrovich', '25345', 'asd@gmail.com'),
  ('lilu', '34542345', 'Tatsiana', 'Sinelnikova', 'Vasilevna', '234242', 'asd@gmail.com'),
  ('tat', '345543A', 'Tatsiana', 'Sinelnikova', 'Ivanovna', 'asd', 'asd@gmail.com'),
  ('max', '3455as', 'Максим', 'Синельников', 'Михайлович', 'asdsa', 'asd@gmail.com'),
  ('vasya', '34da55', 'Василий', 'Савченко', 'Митрофанович', 'sda', 'asd@gmail.com');

DELETE FROM user;

INSERT INTO user (login, password, name, surname, patronymic, role, phone, email) VALUES
  ('pavel', '3455', 'Pavel', 'Sinelnikov', 'Michailovich', 'ADMIN', '3252525', 'asd@gmail.com');


CREATE TABLE language (
  code VARCHAR(5)  NOT NULL,
  name VARCHAR(15) NOT NULL,
  PRIMARY KEY (code)
);

CREATE TABLE drug_neutral (
  id          MEDIUMINT AUTO_INCREMENT,
  need_recipe TINYINT(1) NOT NULL,
  PRIMARY KEY (id)
);
INSERT INTO drug (manufacurer_id, dosage, amount, price, number, need_presciption) VALUES (1, 2, 3, 0.0, 0, 0);
INSERT INTO manufacturer (country_id) VALUES (1);
SELECT code
FROM language
WHERE name = 'russian'

CREATE TABLE translation (
  drug_id       MEDIUMINT   NOT NULL,
  language_code VARCHAR(5)  NOT NULL,
  name          VARCHAR(20) NOT NULL,
  description   TEXT,
  FOREIGN KEY (drug_id) REFERENCES drug_neutral (id),
  FOREIGN KEY (language_code) REFERENCES language (code)
);


CREATE TABLE stock (
  id     MEDIUMINT,
  dosage MEDIUMINT     NOT NULL,
  number MEDIUMINT     NOT NULL,
  price  DECIMAL(7, 2) NOT NULL,
  FOREIGN KEY (id) REFERENCES drug_neutral (id)
);

INSERT INTO stock (id, dosage, number, price) VALUES
  (4, 20, 10, 20),
  (3, 10, 2, 15.2);


SELECT
  name,
  description,
  number,
  dosage,
  need_recipe,
  price
FROM (drug_neutral
  JOIN translation ON drug_neutral.id = translation.drug_id)
  LEFT JOIN stock ON drug_neutral.id = stock.id;
;


SELECT code
FROM language
WHERE name = 'russian';

INSERT INTO drug_translate (drug_id, lang_code, name) VALUES
  (11,'en','name');

INSERT INTO manufacturer_translate (language_code, manufacturer_id, name) VALUES
  ('ru',1,'Белмедпрепараты'),
  ('en',1,'Belmed');
INSERT INTO language (code, name) VALUES
  ('en', 'english'),
  ('ru', 'russian');
SELECT name,id,surname,patronymic,password,login ,role,pnone, email FROM user WHERE login='pavel';

SELECT
  drug.id,
  drug_translate.name,
  drug_translate.composition,
  drug.number,
  drug.amount,
  drug.dosage,
  drug_translate.description,
  drug.need_presciption,
  drug.price,
  manufacturer.id,
  manufacturer.phone_number,
  manufacturer_translate.name
FROM drug
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN manufacturer ON drug.manufacurer_id = manufacturer.id
  INNER JOIN country ON manufacturer.country_id = country.id
  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
  INNER JOIN language ON drug_translate.lang_code = language.code
WHERE language.name = 'russian' AND drug_translate.lang_code = manufacturer_translate.language_code;