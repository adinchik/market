create table categories (
    id          bigserial primary key,
    title       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table products (
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8, 2),
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into categories (title) values
('Food'),
('Electronic');

insert into products (title, price, category_id) values
('Bread', 32.00, 1),
('Milk', 120.00, 1),
('Butter', 320.00, 1),
('Cheese', 500.00, 1),
('Sweets', 50.00, 1),
('Cakes', 30.00, 1),
('Onions', 140.00, 1),
('Tomatos', 160.00, 1),
('Lemonade', 100.00, 1),
('Cucumbers', 110.00, 1),
('IceCream', 70.00, 1),
('Coca-Cola 0.9 л.', 87.00, 1);

create table orders
(
    id              bigserial primary key,
    username        varchar(255),
    total_price     numeric(8, 2),
    address         varchar(255),
    phone_number     varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table orders_items
(
    id                      bigserial primary key,
    order_id                bigint references orders (id),
    product_id              bigint references products (id),
    price_per_product       numeric(8, 2),
    quantity                int,
    price                   numeric(8, 2),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);