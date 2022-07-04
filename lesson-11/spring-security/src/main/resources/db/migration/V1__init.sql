create table users
(
    id       bigserial    not null primary key,
    username     varchar(255) not null unique,
    password varchar(255) not null,
    email    varchar(255) not null unique
);

create table roles
(
    id   bigserial    not null primary key,
    name varchar(255) not null
);

create table privileges
(
    id   bigserial    not null primary key,
    name varchar(255) not null
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table roles_privileges
(
    role_id      bigint not null,
    privilege_id bigint not null,
    primary key (role_id, privilege_id),
    foreign key (privilege_id) references privileges (id),
    foreign key (role_id) references roles (id)
);

insert into users (username, password, email)
values ('admin', '$2a$12$cR9E4quQc0l3yFPlM8Ov.uMSE/e661TsOgruHeCksBqBccTUSeDrm', 'admin@example.com'),
       ('manager', '$2a$12$kLXd5WJn1ai8cV7aipIT/u/QRQykQsdUvtgY3/VX0xC5qpvIc9Kza', 'manager@example.com'),
       ('user', '$2a$12$8.OpcNZWdb4iEH8fXuFkIu6yjxMAdIiOm2xSYATckqR1hDVxRlb5e', 'user@example.com');

insert into roles (name)
values ('ROLE_ADMIN'),
       ('ROLE_MANAGER'),
       ('ROLE_USER');

insert into privileges (name)
values ('ADD_USER'),     -- 1
       ('EDIT_USER'),    -- 2
       ('DELETE_USER'),  -- 3
       ('ADD_POST'),     -- 4
       ('EDIT_POST'),    -- 5
       ('DELETE_POST'); -- 6

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 3);
insert into roles_privileges (role_id, privilege_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 2),
       (2, 4),
       (2, 5),
       (3, 4)
