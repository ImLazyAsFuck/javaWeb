create database if not exists web_app_db;
use web_app_db;

drop table if exists product;
create table product(
                         id int auto_increment primary key,
                         name varchar(150) not null unique,
                         price decimal(15,2) check(price > 0),
                        image varchar(200),
                        description text,
                        status enum('AVAILABLE', 'NOT_AVAILABLE'),
                         created_at datetime default(now())
);

delimiter //

drop procedure if exists find_all_product;
create procedure find_all_product()
begin
    select id, name, price, image, description, status, created_at from product;
end //

drop procedure if exists find_product_by_id;
create procedure find_product_by_id(p_id int)
begin
    select id, name, price, image, description, status, created_at from product where id = p_id;
end //

drop procedure if exists save_product;
create procedure save_product(
    p_name varchar(150),
    p_price decimal(15,2),
    p_image varchar(255),
    p_description text,
    p_status enum('AVAILABLE', 'NOT_AVAILABLE')
)
begin
    insert into product(name, price, image, description, status)
    values (p_name, p_price, p_image, p_description, p_status);
end //

drop procedure if exists update_product;
create procedure update_product(
    p_id int,
    p_name varchar(150),
    p_price decimal(15,2),
    p_image varchar(255),
    p_description text,
    p_status enum('AVAILABLE', 'NOT_AVAILABLE')
)
begin
    update product
    set
        name = p_name,
        price = p_price,
        image = p_image,
        description = p_description,
        status = p_status
    where id = p_id;
end //

drop procedure if exists delete_product;
create procedure delete_product(p_id int)
begin
    delete from product
    where id = p_id;
end //

drop procedure if exists find_product_by_name;
create procedure find_product_by_name(p_name varchar(150))
begin
    select id, name, price, image, description, status, created_at from product where name like concat('%', p_name, '%');
end //

delimiter ;