BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price DECIMAL);
INSERT INTO products (title, price) VALUES
                                        ('box', 10.05),
                                        ('sphere', 20.05),
                                        ('maul', 100.05),
                                        ('door', 50.05),
                                        ('camera', 500.05);

COMMIT;