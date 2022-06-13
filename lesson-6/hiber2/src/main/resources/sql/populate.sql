set schema 'hiber';

BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY,
                       name VARCHAR(255));
INSERT INTO customers (name) VALUES
                                ('Ivanov'),
                                ('Petrov'),
                                ('Sidorov');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY,
                      title VARCHAR(255),
                      cost bigint);
INSERT INTO products (title, cost) VALUES
                                      ('Coffee', 10),
                                      ('Milk', 20),
                                      ('Tea', 30);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id bigserial PRIMARY KEY,
                      customer_id bigint,
                      FOREIGN KEY (customer_id) REFERENCES customers (id));


DROP TABLE IF EXISTS order_products CASCADE;
CREATE TABLE order_products (id bigserial PRIMARY KEY,
                             title VARCHAR(255),
                             cost bigint,
                             actual_product_id bigint,
                             FOREIGN KEY (actual_product_id) REFERENCES product (id)
                            );

DROP TABLE IF EXISTS products_orders CASCADE;
CREATE TABLE products_orders (product_id bigint,
                              order_id bigint,
                              FOREIGN KEY (product_id) REFERENCES product (id),
                              FOREIGN KEY (order_id) REFERENCES orders (id));

END;