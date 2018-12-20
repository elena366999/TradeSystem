use trade_system;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL unique,
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
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  product_id   INT  NOT NULL,
  quantity       INT       NOT NULL default 1,
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
VALUES ('admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_ADMIN');
INSERT INTO users (username, password, role)
VALUES ('user', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_USER');
INSERT INTO users (username, password, role)
VALUES ('user2', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'ROLE_USER');

insert into products (name, price, description)
values ('product1', '2.3', 'description1');
insert into products (name, price, description)
values ('product2', '2.3', 'description2');
insert into products (name, price, description)
values ('product3', '2.3', 'description3');
insert into products (name, price, description)
values ('product4', '2.3', 'description4');
insert into products (name, price, description)
values ('product5', '2.3', 'description5');


insert into items (product_id, quantity)
values(1, 3);

insert into items (product_id, quantity)
values(2, 5);

insert into items (product_id, quantity)
values(4, 1);

insert into items (product_id, quantity)
values(5, 1);


insert into orders (order_status, user_id)
values('NEW', 1);
insert into orders (order_status, user_id)
values('IN_PROCESSING', 2);

insert into order_items (order_id, item_id)
values (1, 1);

insert into order_items (order_id, item_id)
values (1, 2);

insert into order_items (order_id, item_id)
values (2, 3);

insert into order_items (order_id, item_id)
values (1, 4);


