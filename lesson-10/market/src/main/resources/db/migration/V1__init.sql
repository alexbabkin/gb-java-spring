create table categories
(
    id    bigserial primary key,
    title varchar(255)
);
insert into categories (title)
values ('Meat'),
       ('Fish'),
       ('Vegetable'),
       ('Fruit');
create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);
insert into products(title, price, category_id)
values ('Peach', 1, 4),
       ('Orange', 2, 4),
       ('Cucumber', 4, 3),
       ('Tomato', 3, 3),
       ('Pear', 6, 4),
       ('Beef', 2, 1),
       ('Ham', 4, 1),
       ('Cod', 3, 2),
       ('Salmon', 6, 2),
       ('Broccoli', 2, 3),
       ('Carrot', 4, 3),
       ('Garlic', 3, 3),
       ('Onion', 6, 3),
       ('Pea', 2, 3),
       ('Apple', 4, 4),
       ('Banana', 3, 4),
       ('Lemon', 6, 4),
       ('Spinach', 4, 3),
       ('Pike', 3, 2),
       ('Turkey', 6, 1),
       ('Chicken', 10, 1);