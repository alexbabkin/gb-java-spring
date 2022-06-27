create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values ('cat1'),
       ('cat2'),
       ('cat3'),
       ('cat4');
create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);
insert into products(title, price, category_id)
values ('prod1', 1, 4),
       ('prod2', 2, 4),
       ('prod3', 4, 3),
       ('prod4', 3, 3),
       ('prod5', 6, 4),
       ('prod6', 2, 1),
       ('prod7', 4, 1),
       ('prod8', 3, 2),
       ('prod9', 6, 2),
       ('prod10', 2, 3),
       ('prod11', 4, 3),
       ('prod12', 3, 3),
       ('prod13', 6, 3),
       ('prod14', 2, 3),
       ('prod15', 4, 4),
       ('prod16', 3, 4),
       ('prod17', 6, 4),
       ('prod18', 4, 3),
       ('prod19', 3, 2),
       ('prod20', 6, 1),
       ('prod21', 10, 1);