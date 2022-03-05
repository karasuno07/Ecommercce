-- CATEGORY
INSERT INTO public.categories (name, slug, parent_id)
VALUES ('Điện thoại', 'dien-thoai', null),
       ('Laptop', 'laptop', null),
       ('Phụ kiện', 'phu-kien', null),
       ('Tai nghe', 'tai-nghe', 3);

-- BRAND
INSERT INTO public.brands (name, slug, image)
VALUES ('Apple', 'apple', 'img'),
       ('Sam Sung', 'sam-sung', 'img');


-- SUPPLIERS
INSERT INTO public.suppliers (active, address, email, name, phone_number)
VALUES (true, 'Long Thanh Dong Nai', 'huyducvo972001@gmail.com', 'Vo Duc Huy', '0849064659');

-- PRODUCT
INSERT INTO public.products (name, slug, brand_id, category_id, available)
VALUES ('iPhone 13 Pro Max', 'iphone-13-pro-max', 1, 2, true);

-- PRODUCT DETAILS
INSERT INTO public.product_details (product_id, price, discount, in_stock, is_default, images, descriptions)
VALUES (1, 10000.00, 100.00, 2000, true, '{abc}', '{}');

