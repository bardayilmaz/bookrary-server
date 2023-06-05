-- city --
insert into city(id, created, updated, name, plate_number)
values('1', now(), now(), 'İzmir', 35);

insert into library(id, created, updated, name, address, city_id)
values('1', now(), now(), 'IYTE Kütüp', 'IYTE', '1');

insert into users(id, created, updated, first_name, last_name, password, library_id, email, phone_number, role)
values('1', now(), now(), 'Bulent Arda', 'Yilmaz', '$2a$10$5wUz/bDg6tPD2B.ziDx2MeKWFueKaiiFFwr6i.VyhfqUkxXWnna6m',
       '1', 'bardayilmaz35@gmail.com', '+905531066289', 'ADMIN');

-- author --
insert into author(id, created, updated, first_name, last_name)
values ('1', now(), now(), 'Sait Faik', 'Abasıyanık');

-- advert --
insert into advert(id, created, updated, price, advert_status, title, page_count, publication_date, language, book_type,
                 publisher_name, author_id, seller_id)
values('1', now(), now(), 35.5, 'PENDING', 'Berke Udunman', 123, '01-01-2023', 'TURKISH', 'SCIENCE_FICTION', 'İYTE YAYINCILIK', '1', '1');

insert into advert(id, created, updated, price, advert_status, title, page_count, publication_date, language, book_type,
                 publisher_name, author_id, seller_id)
values('2', now(), now(), 35.5, 'PENDING', 'Bülent Arda Yılmaz', 365, '01-01-2023', 'TURKISH', 'SCIENCE_FICTION', 'İYTE YAYINCILIK', '1', '1');

insert into sale(id, created, updated, sale_status)
values('1', now(), now(), 'ACTIVE');
