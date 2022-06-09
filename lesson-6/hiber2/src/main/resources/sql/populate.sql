set schema 'hiber';

BEGIN;

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customer (name) VALUES
                                ('Ivanov'),
                                ('Petrov'),
                                ('Sidorov');

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), cost bigint);
INSERT INTO product (title, cost) VALUES
                                      ('Coffee', 10),
                                      ('Milk', 20),
                                      ('Tea', 30);

DROP TABLE IF EXISTS "order" CASCADE;
CREATE TABLE "order" (id bigserial PRIMARY KEY, customer_id bigint,
                      FOREIGN KEY (customer_id) REFERENCES customer (id));
INSERT INTO "order" (id, customer_id) VALUES
                                          (1, 1),
                                          (2, 1),
                                          (3, 1),
                                          (4, 2),
                                          (5, 2);
;

DROP TABLE IF EXISTS order_details CASCADE;
CREATE TABLE order_details (id bigserial PRIMARY KEY, order_id bigint , product_id bigint, quantity bigint,
                            FOREIGN KEY (product_id) REFERENCES product (id));
INSERT INTO order_details (order_id, product_id, quantity) VALUES
                                                               (1, 1, 2),
                                                               (1, 2, 1),
                                                               (1, 3, 3),
                                                               (2, 1, 1),
                                                               (3, 2, 2),
                                                               (4, 2, 3),
                                                               (5, 3, 3);

END;