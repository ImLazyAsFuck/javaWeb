use ss6;

drop table if exists product_carts;
drop table if exists products;

create table if not exists products (
    id int auto_increment primary key,
    name varchar(255) not null,
    price double not null,
    image_url varchar(500) not null
);

create table if not exists product_carts (
    id int auto_increment primary key,
    session_id varchar(100) not null,
    product_id int not null,
    quantity int not null default 1,
    foreign key (product_id) references products(id)
);

delimiter //

drop procedure if exists find_all_products//
create procedure find_all_products()
begin
    select id, name, price, image_url from products;
end //

drop procedure if exists find_product_by_id//
create procedure find_product_by_id(in p_id int)
begin
    select id, name, price, image_url from products where id = p_id;
end //

drop procedure if exists add_product//
create procedure add_product(in p_name varchar(255), in p_price double, in p_image_url varchar(500))
begin
    insert into products(name, price, image_url) values (p_name, p_price, p_image_url);
end //

drop procedure if exists update_product//
create procedure update_product(in p_id int, in p_name varchar(255), in p_price double, in p_image_url varchar(500))
begin
    update products set name = p_name, price = p_price, image_url = p_image_url where id = p_id;
end //

drop procedure if exists delete_product//
create procedure delete_product(in p_id int)
begin
    delete from products where id = p_id;
end //

drop procedure if exists find_cart_by_session_id//
create procedure find_cart_by_session_id(in p_session_id varchar(100))
begin
    select pc.*, p.name, p.price, p.image_url
    from product_carts pc
    join products p on pc.product_id = p.id
    where pc.session_id = p_session_id;
end //

drop procedure if exists find_cart_item//
create procedure find_cart_item(in p_session_id varchar(100), in p_product_id int)
begin
    select * from product_carts
    where session_id = p_session_id and product_id = p_product_id;
end //

drop procedure if exists add_to_cart//
create procedure add_to_cart(in p_session_id varchar(100), in p_product_id int, in p_quantity int)
begin
    insert into product_carts(session_id, product_id, quantity)
    values (p_session_id, p_product_id, p_quantity);
end //

drop procedure if exists update_cart_quantity//
create procedure update_cart_quantity(in p_id int, in p_quantity int)
begin
    update product_carts set quantity = p_quantity where id = p_id;
end //

drop procedure if exists remove_from_cart//
create procedure remove_from_cart(in p_id int)
begin
    delete from product_carts where id = p_id;
end //

drop procedure if exists clear_cart//
create procedure clear_cart(in p_session_id varchar(100))
begin
    delete from product_carts where session_id = p_session_id;
end //

delimiter ;
