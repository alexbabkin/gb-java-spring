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

create table users
(
    id         bigserial primary key,
    username   varchar(30) unique,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);