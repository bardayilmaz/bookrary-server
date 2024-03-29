-- city --
insert into city(id, created, updated, name, plate_number)
values('1', now(), now(), 'İzmir', 35);

insert into library(id, created, updated, name, address, city_id)
values('1', now(), now(), 'IYTE Kütüp', 'IYTE', '1');

insert into users(id, created, updated, first_name, last_name, password, library_id, email, phone_number, role)
values('1', now(), now(), 'Bulent Arda', 'Yilmaz', '$2a$10$5wUz/bDg6tPD2B.ziDx2MeKWFueKaiiFFwr6i.VyhfqUkxXWnna6m',
       '1', 'bardayilmaz35@gmail.com', '+905531066289', 'ADMIN');

insert into users(id, created, updated, first_name, last_name, password, library_id, email, phone_number, role)
values('2', now(), now(), 'Berke', 'Udunman', '$2a$10$5wUz/bDg6tPD2B.ziDx2MeKWFueKaiiFFwr6i.VyhfqUkxXWnna6m',
       '1', 'berkeudunman@gmail.com', '+905531066289', 'ADMIN');

-- author --
insert into author(id, created, updated, first_name, last_name)
values ('1', now(), now(), 'Sait Faik', 'Abasıyanık');

insert into author(id, created, updated, first_name, last_name)
values ('2', now(), now(), 'Şevket Süreyya', 'Aydemir');

-- advert --
insert into advert(id, created, updated, price, advert_status, title, page_count, publication_date, language, book_type,
                 publisher_name, author_id, seller_id)
values('1', now(), now(), 35.5, 'PENDING', 'Otostopçunun Galaksi Rehber', 123, '01-01-2023', 'TURKISH', 'SCIENCE_FICTION', 'İYTE YAYINCILIK', '1', '1');

insert into advert(id, created, updated, price, advert_status, title, page_count, publication_date, language, book_type,
                 publisher_name, author_id, seller_id)
values('2', now(), now(), 35.5, 'PENDING', 'Dune', 365, '01-01-2023', 'TURKISH', 'SCIENCE_FICTION', 'İYTE YAYINCILIK', '1', '1');

insert into advert(id, created, updated, price, advert_status, title, page_count, publication_date, language, book_type,
                   publisher_name, author_id, seller_id)
values('3', now(), now(), 35.5, 'PENDING', 'İyte Tarihi', 365, '01-01-2023', 'TURKISH', 'HISTORY', 'İYTE YAYINCILIK', '2', '1');

insert into sale(id, created, updated, sale_status, advert_id, seller_id, buyer_id)
values('1', now(), now(), 'ACTIVE', '1', '1', '2');

insert into sale(id, created, updated, sale_status, advert_id, seller_id, buyer_id)
values('2', now(), now(), 'ACTIVE', '2', '1', '2');

insert into sale(id, created, updated, sale_status, advert_id, seller_id, buyer_id)
values('3', now(), now(), 'ACTIVE', '3', '1', '2');
