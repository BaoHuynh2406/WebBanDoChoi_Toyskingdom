create database if not exists toyskingdomdata;

drop database toyskingdomdata

use toyskingdomdata;



create table users
(
    id_user      int auto_increment primary key,
    email        varchar(50) unique     not null,
    password     nvarchar(200)          not null,
    full_name    nvarchar(50)           not null,
    phone_number varchar(20),
    address      nvarchar(200),
    birthday     date,
    role         ENUM ('USER', 'ADMIN') not null default 'USER',
    active       bit                             default 1
) AUTO_INCREMENT = 100000;

CREATE TABLE categories
(
    id_category   varchar(10) PRIMARY KEY,
    category_name NVARCHAR(50) NOT NULL,
    description   TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
);



create table products
(
    id_product   int auto_increment primary key,
    id_category  varchar(10),
    product_name nvarchar(50) not null,
    des          TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    image        nvarchar(100)  default 'product.png',
    price        decimal(14, 2) default 0,
    unit         nvarchar(20)   default 'Cái',
    quantity     decimal(10, 2) default 0,
    active       bit            default 1,
    foreign key (id_category) references categories (id_category)
);


create table discounts
(
    id_discount      int auto_increment primary key,
    id_prduct        int,
    discount_percent decimal(5, 2) not null,
    start_day        datetime      not null,
    end_day          datetime      not null,
    active           bit default 1,
    foreign key (id_prduct) references products (id_product)
);

create table orders
(
    id_order   int auto_increment primary key,
    id_user    int,
    order_date datetime                              default current_timestamp,
    total      decimal(15, 2),
    status     ENUM ('PENDING', 'PAID', 'CANCELLED') default 'PENDING',
    foreign key (id_user) references users (id_user)
);

create table order_items
(
    id_order_item  int auto_increment primary key,
    id_order       int,
    id_product     int,
    order_quantity int          default 1,
    price          decimal(13, 2) not null,
    unit           nvarchar(20) default 'Cái',
    foreign key (id_order) references orders (id_order),
    foreign key (id_product) references products (id_product)
);

# INSERT Dữ liệu
INSERT INTO users (email, password, full_name, phone_number, address, birthday, role, active)
VALUES ('huynhbaomts2004@gmail.com', '123123', 'Bảo', '0388319013', 'Gò vấp, Hồ Chí Minh', '2000-01-01', 'ADMIN', 1),
       ('kiet@gmail.com', '123123', 'Đình Kiệt', '0388319013', 'Gò vấp, Hồ Chí Minh', '2000-01-01', 'ADMIN', 1),
       ('giabao@gmail.com', '123123', 'Gia Bảo', '0388319013', 'Gò vấp, Hồ Chí Minh', '2000-01-01', 'ADMIN', 1),
       ('lynguyenhoa102@gmail.com', '123123', 'Hoà', '0338997477', 'Gò vấp, Hồ Chí Minh', '2004-05-08', 'ADMIN', 1),
       ('superherovhv@gmail.com', '123123', 'Zinh', '0338997477', 'Gò vấp, Hồ Chí Minh', '2004-05-08', 'ADMIN', 1),
       ('han@gmail.com', '123123', 'Hữu Hàn', '0388319013', 'Gò vấp, Hồ Chí Minh', '2000-06-24', 'ADMIN', 1);

insert into categories(id_category, category_name, description)
values ('RB', 'Robot', 'robot, siêu anh hùng'),
       ('BB', 'Búp bê', 'Búp bê baby biết múa biết bay');



INSERT INTO products (product_name, id_category, des, image, price, unit, quantity, active)
VALUES ('Xe Đạp Trẻ Em', 'RB', 'Xe đạp 3 bánh cho trẻ em, màu xanh lá', 'OIP.jpg', 850000.00, 'Cái', 15, 1),
       ('Búp Bê Barbie', 'BB', 'Búp bê Barbie phiên bản công chúa', 'bup-be.jpg', 550000.00, 'Cái', 30, 1),
       ('Lego Classic', 'BB', 'Bộ xếp hình Lego Classic với 500 mảnh', 'lego.jpg', 1200000.00, 'Hộp', 20, 1),
       ('Đồ Chơi Nhà Bếp', 'BB', 'Bộ đồ chơi nhà bếp mini', 'dochoibep.jpg', 300000.00, 'Bộ', 50, 1),
       ('Ô Tô Điều Khiển Từ Xa', 'BB', 'Ô tô điều khiển từ xa màu đỏ', 'xe.jpg', 650000.00, 'Cái', 25, 1),
       ('Gấu Bông Teddy', 'RB', 'Gấu bông Teddy cỡ lớn, cao 1m', 'gauteddy.jpg', 450000.00, 'Cái', 40, 1),
       ('Bảng Vẽ Thông Minh', 'RB', 'Bảng vẽ thông minh cho bé', 'bangve.jpg', 200000.00, 'Cái', 35, 1),
       ('Bộ Đồ Chơi Lắp Ráp', 'RB', 'Bộ đồ chơi lắp ráp mô hình ô tô', 'dolaprap.jpg', 350000.00, 'Bộ', 45, 1),
       ('Bộ Xếp Hình 3D', 'RB', 'Bộ xếp hình 3D sáng tạo', 'lego3d.jpg', 500000.00, 'Bộ', 20, 1),
       ('Đàn Piano Điện Tử', 'RB', 'Đàn piano điện tử mini cho bé', 'dan.jpg', 1500000.00, 'Cái', 10, 1);

# delete from discounts

INSERT INTO discounts (id_prduct, discount_percent, start_day, end_day, active)
VALUES (1, 10.00, '2024-01-01 00:00:00', '2024-06-10 23:59:59', 1),
       (2, 15.00, '2024-01-01 00:00:00', '2024-06-15 23:59:59', 1),
       (3, 20.00, '2024-01-01 00:00:00', '2024-06-20 23:59:59', 1),
       (4, 25.00, '2024-01-15 00:00:00', '2024-06-25 23:59:59', 1),
       (5, 30.00, '2024-01-20 00:00:00', '2024-06-30 23:59:59', 1),
       (6, 35.00, '2024-01-01 00:00:00', '2024-06-07 23:59:59', 1),
       (7, 40.00, '2024-01-08 00:00:00', '2024-06-14 23:59:59', 1),
       (8, 45.00, '2024-01-15 00:00:00', '2024-06-21 23:59:59', 1),
       (9, 50.00, '2024-01-22 00:00:00', '2024-06-28 23:59:59', 1),
       (10, 55.00, '2024-01-25 00:00:00', '2024-07-05 23:59:59', 1);

INSERT INTO orders (id_user, total, status)
VALUES (100000, 500000.00, 'PAID'),
       (100000, 1500000.00, 'PENDING'),
       (100000, 750000.00, 'CANCELLED'),
       (100000, 1200000.00, 'PAID'),
       (100000, 900000.00, 'PENDING');



INSERT INTO order_items (id_order, id_product, order_quantity, price, unit)
VALUES (1, 1, 1, 850000.00, 'Cái'),
       (1, 2, 1, 550000.00, 'Cái'),
       (2, 3, 2, 1200000.00, 'Hộp'),
       (2, 4, 1, 300000.00, 'Bộ'),
       (3, 5, 1, 650000.00, 'Cái'),
       (3, 6, 1, 450000.00, 'Cái'),
       (4, 7, 1, 200000.00, 'Cái'),
       (4, 8, 2, 350000.00, 'Bộ'),
       (5, 9, 1, 500000.00, 'Bộ'),
       (5, 10, 1, 1500000.00, 'Cái');


# Bảng phụ

CREATE VIEW product_feature AS
SELECT p.id_product,
       p.id_category,
       p.product_name,
       p.des,
       p.image,
       p.price,
       COALESCE(d.discount_percent, 0) AS discount_percent
FROM products p
         LEFT JOIN
     discounts d
     ON
         p.id_product = d.id_prduct
             AND p.active = 1
             AND d.active = 1
             AND NOW() BETWEEN d.start_day AND d.end_day;

select *
from product_feature
where product_name like '%Xe đạp trẻ em%'

# PROCEDURE pờ rô si trơ
CREATE PROCEDURE get_Quantity_Product(IN start INT, IN quantity INT)
BEGIN
    SELECT id_product,
           id_category,
           product_name,
           des,
           image,
           price,
           discount_percent
    FROM product_feature
    LIMIT start, quantity;
END;



select * from orders
