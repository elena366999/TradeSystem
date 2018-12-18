use trade_system;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role     VARCHAR(40)  NOT NULL
);

CREATE TABLE items (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL,
  price       DOUBLE       NOT NULL,
  description VARCHAR(255) NOT NULL,
  image       BLOB,
  quantity    INT NOT NULL DEFAULT 1
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
VALUES ('admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_ADMIN');
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_USER');

