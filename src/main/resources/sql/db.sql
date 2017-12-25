INSERT INTO pharmacy.user (login, password, name, surname, patronymic, phone, email) VALUES
  ('miha', '3522234', 'Mihail', 'Sinelnikov', 'Petrovich', '25345', 'asd@gmail.com'),
  ('lilu', '34542345', 'Tatsiana', 'Sinelnikova', 'Vasilevna', '234242', 'asd@gmail.com'),
  ('tat', '345543A', 'Tatsiana', 'Sinelnikova', 'Ivanovna', 'asd', 'asd@gmail.com'),
  ('max', '3455as', 'Максим', 'Синельников', 'Михайлович', 'asdsa', 'asd@gmail.com'),
  ('vasya', '34da55', 'Василий', 'Савченко', 'Митрофанович', 'sda', 'asd@gmail.com');

DELETE FROM user;

INSERT INTO user (login, password, name, surname, patronymic, role, phone, email) VALUES
  ('pavel', '3455', 'Pavel', 'Sinelnikov', 'Michailovich', 'ADMIN', '3252525', 'asd@gmail.com');

SELECT
  id,
  name
FROM user
WHERE surname = 'Sinelnikov';

SELECT *
FROM user;

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
  (11, 'en', 'name');

INSERT INTO manufacturer_translate (language_code, manufacturer_id, name) VALUES
  ('ru', 1, 'Белмедпрепараты'),
  ('en', 1, 'Belmed');
INSERT INTO language (code, name) VALUES
  ('en', 'english'),
  ('ru', 'russian');
SELECT
  name,
  id,
  surname,
  patronymic,
  password,
  login,
  role,
  pnone,
  email
FROM user
WHERE login = 'pavel';


/*Показать названия препаратов и названия их производителей(на русском языке)*/
SELECT
  drug_translate.name         AS name,
  drug.price                  AS price,
  manufacturer_translate.name AS manufacturer
FROM drug
  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
  INNER JOIN language ON manufacturer_translate.language_code = language.language_code
WHERE language.name = 'russian';


/*Показать названия перпаратов, цена которых меньше 1 рубля*/
SELECT
  MAX(orders.number)                   AS number,
  concat(user.name, " ", user.surname) AS user,
  drug.price                           AS price
FROM orders
  INNER JOIN user ON user.id = orders.client_id
  INNER JOIN drug ON orders.drug_id = drug.id
GROUP BY drug.price
HAVING drug.price > 30;


SELECT
  user.name  AS name,
  user.phone AS phone
FROM orders
  INNER JOIN drug ON orders.drug_id = drug.id
  INNER JOIN user ON orders.client_id = user.id
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN language ON drug_translate.lang_code = language.language_code
WHERE drug_translate.name = 'Аевит' AND language.name = 'russian'
UNION SELECT
        user.name  AS name,
        user.phone AS phone
      FROM orders
        INNER JOIN drug ON orders.drug_id = drug.id
        INNER JOIN user ON orders.client_id = user.id
        INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id
        INNER JOIN country ON manufacturer.country_code = country.code
        INNER JOIN country_translate ON country.code = country_translate.country_code
      WHERE country_translate.name = 'Республика Беларусь';


/*Показать названия только белорусских производителей(на русском языке)*/
SELECT manufacturer_translate.name AS name
FROM manufacturer
  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
  INNER JOIN country ON manufacturer.country_code = country.code
  INNER JOIN country_translate ON country.code = country_translate.country_code
WHERE country_translate.name = 'Республика Беларусь' AND country_translate.lan_code = 'ru';


/*Показать названия и описания всех препаратов(фармацевт заполняет информацию о препаратах))*/
SELECT
  drug.id                    AS id,
  drug_translate.name        AS name,
  drug_translate.description AS description,
  drug_translate.composition AS composition,
  language.name              AS language
FROM drug
  LEFT JOIN drug_translate ON drug_translate.drug_id = drug.id
  INNER JOIN language ON language.language_code = drug_translate.lang_code;


/*Показать имя и отчество пользователей с фамилией Иванов(при поиске доктором нужного пользователя)*/
SELECT CONCAT(user.name, " ", user.patronymic) AS name_surname
FROM user
WHERE surname = 'Иванов';


/*Сколько дней назад был сделан заказ*/
SELECT
  user.login                             AS login,
  drug_translate.name                    AS drug,
  DATEDIFF(CURDATE(), DATE(orders.date)) AS days
FROM orders
  INNER JOIN user ON orders.client_id = user.id
  INNER JOIN drug_translate ON orders.drug_id = drug_translate.drug_id;


/*Показать названия только белорусских производителей(на русском языке)*/
SELECT drug_translate.name AS name
FROM manufacturer
  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
  INNER JOIN drug ON manufacturer.id = drug.manufacturer_id
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN country ON manufacturer.country_code = country.code
  INNER JOIN country_translate ON country.code = country_translate.country_code
  INNER JOIN language ON drug_translate.lang_code = language.language_code
WHERE country_translate.name = 'Республика Беларусь' AND language.name = 'russian';


SELECT manufacturer_translate.name AS name
FROM manufacturer
  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
  INNER JOIN country ON manufacturer.country_code = country.code
  INNER JOIN country_translate ON country.code = country_translate.country_code
  INNER JOIN language ON manufacturer_translate.language_code = language.language_code
WHERE country_translate.name = 'Российская Федерация' AND language.name = 'russian';


SELECT
  drug_translate.name        AS name,
  drug_translate.description AS description,
  drug.price                 AS price
FROM drug
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN language ON drug_translate.lang_code = language.language_code
WHERE drug.id NOT IN (SELECT drug.id
                      FROM drug
                        INNER JOIN orders ON drug.id = orders.drug_id);

SELECT
  concat(user.name, " ", user.surname) AS name,
  COUNT(orders.client_id)              AS number
FROM orders
  INNER JOIN user ON orders.client_id = user.id
WHERE orders.drug_id IN (SELECT drug.id
                         FROM drug
                           INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id
                           INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
                           INNER JOIN language ON manufacturer_translate.language_code = language.language_code
                           INNER JOIN country ON manufacturer.country_code = country.code
                           INNER JOIN country_translate ON country.code = country_translate.country_code
                         WHERE country_translate.name = 'Республика Беларусь')
GROUP BY drug_id;

/*SELECT drug_translate.name
FROM drug
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN language ON drug_translate.lang_code = language.language_code
WHERE language.name = 'russian'
ORDER BY drug_translate.name
LIMIT 100, 10*/

SELECT drug_translate.name
FROM drug_translate
  INNER JOIN drug ON drug_translate.drug_id = drug.id
WHERE drug_translate.name LIKE '%?%';


INSERT INTO user (login, name, surname, password, patronymic, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?);

/*SELECT drug.id
FROM drug
WHERE manufacturer_id = 1 AND drug.dosage = 150;*/


SELECT country_translate.country_code
FROM country_translate
WHERE country_translate.lan_code = 'ru' AND country_translate.name = 'Республика Беларусь';

SELECT
  drug_translate.name,
  drug.price,
  orders.date,
  orders.number
FROM orders
  INNER JOIN user ON orders.client_login = user.login
  INNER JOIN drug ON orders.drug_id = drug.id
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
WHERE
  user.login = 'login8xdtunvdwm' AND drug_translate.lang_name = 'russian'
LIMIT 3 OFFSET 0;

INSERT INTO user (login, password, name, surname, patronymic, phone, email)
VALUES ('login8o5vj4mdkd', '54157515', 'Генрих', 'Синельников', 'Алексеевич', '+375656381587', '8o5vj4mdkd@gmail.com'),
  ('loginxl5k8m24lx', '32880767', 'Алексей', 'Гапеенко', 'Федорович', '+375410634274', 'xl5k8m24lx@gmail.com');
SELECT
  user.name,
  user.surname,
  user.patronymic,
  user.password,
  user.login,
  user.role,
  user.phone,
  user.email
FROM test.user
LIMIT 3 OFFSET 0;

SELECT
  drug.id,
  drug_translate.name,
  drug_translate.composition,
  drug.number,
  drug.amount,
  drug.dosage,
  drug_translate.description,
  drug.need_prescription,
  drug.price,
  manufacturer.id,
  manufacturer.phone_number,
  manufacturer_translate.name,
  country_translate.name
FROM drug
  INNER JOIN drug_translate ON drug.id = drug_translate.drug_id
  INNER JOIN manufacturer ON drug.manufacturer_id = manufacturer.id
  INNER JOIN country ON manufacturer.country_code = country.code
  INNER JOIN country_translate ON country.code = country_translate.country_code
  INNER JOIN manufacturer_translate ON manufacturer.id = manufacturer_translate.manufacturer_id
WHERE drug_translate.lang_name = 'russian' AND manufacturer_translate.language_name = drug_translate.lang_name AND
      country_translate.lan_name = drug_translate.lang_name
LIMIT 10 OFFSET 10;

TRUNCATE test.user;

INSERT INTO orders (client_login, drug_id, number, date) VALUES ();
DELETE FROM test.user
WHERE user.login = 'login8o5vj4mdkd' OR user.login = 'loginxl5k8m24lx';


SELECT *
FROM user
WHERE login = ?;
