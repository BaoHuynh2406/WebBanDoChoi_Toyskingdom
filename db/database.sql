create database if not exists toyskingdomdata;

use toyskingdomdata;




create table users(
    id_user int auto_increment primary key,
    email varchar(50) unique not null,
    password nvarchar(200) not null ,
    full_name nvarchar(50) not null,
    phone_number varchar(20),
    address nvarchar(200),
    birthday date,
    role ENUM('USER', 'ADMIN') not null default 'USER',
    active bit default 1
) AUTO_INCREMENT=100000;

create table products(
    id_product int auto_increment primary key,
    product_name nvarchar(50) not null,
    des TEXT,
    image nvarchar(100) default 'product.png',
    price decimal(14,2) default 0,
    unit nvarchar(20) default 'Cái',
#     Thiếu loại hàng cần bổ sung
    quantity decimal(10,2) default 0,
    active bit default 1
);


create table discounts(
    id_discount int auto_increment primary key,
    id_prduct int,
    discount_percent decimal(5,2) not null,
    start_day datetime not null,
    end_day datetime not null,
    active bit default 1,
    foreign key (id_prduct) references products(id_product)
);

create table orders(
    id_order int auto_increment primary key,
    id_user int,
    order_date datetime default current_timestamp,
    total decimal(15,2),
    status ENUM('PENDING', 'PAID', 'CANCELLED') default 'PENDING',
    foreign key (id_user) references users(id_user)
);

create table order_items(
    id_order_item int auto_increment primary key,
    id_order int,
    id_product int,
    order_quantity int default 1,
    price decimal(13,2) not null,
    unit nvarchar(20) default 'Cái',
    foreign key (id_order) references orders(id_order),
    foreign key (id_product) references products(id_product)
);

# INSERT Dữ liệu
INSERT INTO users (email, password, full_name, phone_number, address, birthday, role, active) VALUES
                                                                                                  ('huynhbaomts2004@gmail.com', '123123', 'Huỳnh Thiên Bảo', '0388319013', 'Gò vấp, Hồ Chí Minh', '2004-06-24', 'ADMIN', 1);
