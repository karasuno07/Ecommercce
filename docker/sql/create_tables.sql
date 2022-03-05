-- BRANDS
create table brands
(
    id    bigserial     not null primary key,
    image text        not null,
    name  varchar(50) not null,
    slug  varchar(50) not null
);

-- CATEGORIES
create table categories
(
    id        bigserial       not null primary key,
    name      varchar(100) not null,
    slug      varchar(100) not null,
    parent_id bigint
        constraint fksaok720gsu4u2wrgbk10b5n8d
            references categories
);

--CATEGORY_BRAND
create table categories_brands
(
    brand_id    bigint not null
        constraint fkq3o4qye1jl100p2l0hbfw4fq1
            references categories,
    category_id bigint not null
        constraint fkpib87j5f9bmld7jgpak531njf
            references brands
);

--SUPPILIERS
create table suppliers
(
    id           bigserial       not null primary key,
    active       boolean      not null,
    address      varchar(200) not null,
    email        varchar(100) not null,
    name         varchar(100) not null,
    phone_number varchar(12)  not null
);

--ROLES
create table roles
(
    id          bigserial      not null primary key,
    name        varchar(255) not null
);

--USERS
create table users
(
    id            bigserial       not null primary key,
    active        boolean      not null,
    address       varchar(200) not null,
    date_of_birth date         not null,
    email         varchar(100) not null,
    full_name     varchar(50)  not null,
    gender        boolean      not null,
    image         text         not null,
    password      varchar(100) not null,
    phone_number  varchar(12)  not null,
    username      varchar(50)  not null,
    role_id       bigint
        constraint fkp56c1712k691lhsyewcssf40f
            references roles
);

--PRODUCTS
create table products
(
    id          bigserial       not null primary key,
    available   boolean      not null,
    name        varchar(100) not null,
    slug        varchar(100) not null,
    brand_id    bigint       not null
        constraint fka3a4mpsfdf4d2y6r8ra3sc8mv
            references brands,
    category_id bigint       not null
        constraint fkog2rp4qthbtt2lfyhfo32lsw9
            references categories
);

--PRODUCT DETAILS
create table product_details
(
    id           bigserial      not null primary key,
    descriptions jsonb          not null,
    discount     numeric(19, 2) not null,
    images       text[],
    in_stock     integer        not null,
    is_default   boolean        not null,
    price        numeric(19, 2) not null,
    product_id   bigint
        constraint fknfvvq3meg4ha3u1bju9k4is3r
            references products
);

-- PRODUCT_REVIEWS
create table product_reviews
(
    id             bigserial  not null primary key,
    comment        text    not null,
    commented_date timestamp,
    rating         integer not null,
    product_id     bigint
        constraint fk35kxxqe2g9r4mww80w9e3tnw9
            references products,
    customer_id    bigint
        constraint fkhmbqmkl8r398rwsrwhv4urr1a
            references users
);

--RECEIPTS
create table receipts
(
    id           bigserial    not null primary key,
    created_date timestamp not null,
    receipt_code text      not null,
    supplier_id  bigint
        constraint fk4ksphcrrl2epyxvdqwo0gat9d
            references suppliers,
    user_id      bigint    not null
        constraint fk7t0uo7yxjck29e967rny84ky4
            references users
);

--RECEIPT_DETAILS
create table receipt_details
(
    id         bigserial         not null
        primary key,
    price      numeric(19, 2) not null,
    quantity   integer        not null,
    product_id bigint
        constraint fk5mjbgdjnq1ynx0g9tktny2ot
            references products,
    receipt_id bigint
        constraint fkgg9qo0w1vjcu9ridx36dyrhn2
            references receipts
);

--ORDERS
create table orders
(
    id                 bigserial       not null
        primary key,
    address            varchar(200) not null,
    created_date       timestamp    not null,
    email              varchar(100) not null,
    last_receive_date  timestamp,
    last_shipping_date timestamp,
    order_code         text         not null,
    phone_number       varchar(12)  not null,
    recipient_name     varchar(100) not null,
    status             varchar(255)  not null,
    customer_id        bigint
        constraint fksjfs85qf6vmcurlx43cnc16gy
            references users
);

-- ORDER_DETAILS
create table order_details
(
    id         bigserial         not null
        primary key,
    price      numeric(19, 2) not null,
    quantity   integer        not null,
    order_id   bigint
        constraint fkjyu2qbqt8gnvno9oe9j2s2ldk
            references orders,
    product_id bigint
        constraint fk4q98utpd73imf4yhttm3w0eax
            references products
);


