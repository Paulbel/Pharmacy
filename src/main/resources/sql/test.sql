INSERT INTO country (code) VALUES
  ('ru'),
  ('by');

INSERT INTO language (language_name) VALUES
  ('belarusian'),
  ('russian');


INSERT INTO country_translate (country_code, lan_name, name) VALUES
  ('ru', 'russian', 'Российская Федерация'),
  ('ru', 'english', 'Russian Federation'),
  ('by', 'russian', 'Республика Беларусь'),
  ('by', 'english', 'Republic of Belarus');

INSERT INTO user (login, password, name, surname, phone, email) VALUES
  ('login8o5vj4mdkd', '54157515', 'Генрих', 'Синельников', '+375656381587', '8o5vj4mdkd@gmail.com'),
  ('loginxl5k8m24lx', '32880767', 'Алексей', 'Гапеенко', '+375410634274', 'xl5k8m24lx@gmail.com'),
  ('login4o47zru1qt', '13718854', 'Евгений', 'Артемьев', '+375070676621', '4o47zru1qt@gmail.com'),
  ('loginsoxy3dh1oh', '73541301', 'Максим', 'Артемьев', '+375228106402', 'soxy3dh1oh@gmail.com'),
  ('loginhmbzyff23i', '15351217', 'Генрих', 'Борискин', '+375261224174', 'hmbzyff23i@gmail.com');

INSERT INTO manufacturer (phone_number, country_code, email) VALUES
  ('+(375 232) 45-91-34, +(375 232) 45-92-71', 'by', ''),
  ('', 'cz', '');


INSERT INTO manufacturer_translate (language_name, manufacturer_id, name, address) VALUES
  ('russian', 1, '«Медпласт», ОАО', '246051, г. Гомель, ул. Объездная, 12'),
  ('russian', 2, 'Зентива', '');
