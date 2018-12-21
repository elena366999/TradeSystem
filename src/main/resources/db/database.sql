USE trade_system;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role     VARCHAR(40)  NOT NULL
);

CREATE TABLE products (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  price       DOUBLE       NOT NULL,
  description VARCHAR(255) NOT NULL,
  image       LONGBLOB
);

CREATE TABLE items (
  id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  product_id INT NOT NULL,
  quantity   INT NOT NULL DEFAULT 1,
  FOREIGN KEY (product_id) REFERENCES products (id)

);

CREATE TABLE orders (
  id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_status VARCHAR(40) NOT NULL,
  user_id      INT         NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE order_items (
  order_id INT NOT NULL,
  item_id  INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (item_id) REFERENCES items (id)
);

INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_ADMIN');   /* password: 12345678 */
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_USER');     /* password: 12345678 */
INSERT INTO users (username, password, role)
VALUES ('user2', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_USER');    /* password: 12345678 */


INSERT INTO products (name, price, description)
VALUES ('product1', '5.9', 'description1');
INSERT INTO products (name, price, description)
VALUES ('product2', '2.3', 'description2');
INSERT INTO products (name, price, description)
VALUES ('product3', '10.2', 'description3');
INSERT INTO products (name, price, description)
VALUES ('product4', '4.0', 'description4');
INSERT INTO products (name, price, description)
VALUES ('product5', '2.3', 'description5');


INSERT INTO items (product_id, quantity)
VALUES (1, 3);
INSERT INTO items (product_id, quantity)
VALUES (2, 5);
INSERT INTO items (product_id, quantity)
VALUES (4, 1);
INSERT INTO items (product_id, quantity)
VALUES (5, 1);


INSERT INTO orders (order_status, user_id)
VALUES ('NEW', 1);
INSERT INTO orders (order_status, user_id)
VALUES ('IN_PROCESSING', 2);


INSERT INTO order_items (order_id, item_id)
VALUES (1, 1);
INSERT INTO order_items (order_id, item_id)
VALUES (1, 2);
INSERT INTO order_items (order_id, item_id)
VALUES (2, 3);
INSERT INTO order_items (order_id, item_id)
VALUES (1, 4);


