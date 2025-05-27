create database if not exists ss12;
use ss12;

drop table if exists products;
create table products(
    id int auto_increment primary key,
    name varchar(150) not null,
    price decimal(15,2) check(price > 0) not null,
    quantity int check(quantity > 0) not null
);

delimiter //

drop procedure if exists find_all_product;
create procedure find_all_product()
begin
    select id, name, price, quantity from products;
end //

drop procedure if exists find_product_by_id;
create procedure find_product_by_id(p_id int)
begin
    select id, name, price, quantity from products where id = p_id;
end //

drop procedure if exists save_product;
create procedure save_product(
    p_name varchar(150),
    p_price decimal(15,2),
    p_quantity int
)
begin
    insert into products(name, price, quantity)
        values (p_name, p_price, p_quantity);
end //

drop procedure if exists update_product;
create procedure update_product(
    p_id int,
    p_name varchar(150),
    p_price decimal(15,2),
    p_quantity int
)
begin
    update products
        set
            name = p_name,
            price = p_price,
            quantity = p_quantity
    where id = p_id;
end //

drop procedure if exists delete_product;
create procedure delete_product(p_id int)
begin
    delete from products
        where id = p_id;
end //

delimiter ;