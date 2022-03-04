-- CATEGORY
INSERT INTO public.categories (id, name, slug, parent_id) VALUES (1, 'Điện thoại', 'dien-thoai', null);
INSERT INTO public.categories (id, name, slug, parent_id) VALUES (2, 'Laptop', 'laptop', null);
INSERT INTO public.categories (id, name, slug, parent_id) VALUES (13, 'Phụ kiện', 'phu-kien', null);
INSERT INTO public.categories (id, name, slug, parent_id) VALUES (14, 'Tai nghe', 'tai-nghe', 13);

-- BRAND
INSERT INTO public.brands (id, image, name, slug) VALUES (1, 'img', 'Apple', 'apple');
INSERT INTO public.brands (id, image, name, slug) VALUES (2, 'img', 'Sam Sung', 'sam-sung');

-- SUPPLIERS
INSERT INTO public.suppliers (id, active, address, email, name, phone_number) VALUES (2, true, 'Long Thanh Dong Nai', 'huyducvo972001@gmail.com', 'Vo Duc Huy', '0849064659');

-- PRODUCT
INSERT INTO public.products (id, available, name, slug, brand_id, category_id) VALUES (1, true, 'iPhone 13 Pro Max', 'iphone-13-pro-max', 1, 2);

-- PRODUCT DETAILS
INSERT INTO public.product_details (id, descriptions, discount, images, in_stock, is_default, price, product_id) VALUES (5, '{}', 100.00, '{abc}', 2000, false, 10000.00, 1);

