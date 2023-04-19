insert into library(id, created, updated, name, address)
values('1', now(), now(), 'IYTE Kütüp', 'IYTE');

insert into users(id, created, updated, first_name, last_name, password, library_id, email, phone_number, role)
values('1', now(), now(), 'Bulent Arda', 'Yilmaz', '$2a$10$5wUz/bDg6tPD2B.ziDx2MeKWFueKaiiFFwr6i.VyhfqUkxXWnna6m',
       '1', 'bardayilmaz35@gmail.com', '+905531066289', 'ADMIN');