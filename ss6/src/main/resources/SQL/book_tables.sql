drop database if exists ss6;
create database ss6;
use ss6;

drop table if exists book;
create table book(
    id int primary key auto_increment,
    title varchar(150) not null,
    author varchar(150) not null,
    category varchar(150) not null,
    stock int check(stock > 0)
);

delimiter //
drop procedure if exists find_all_book;
create procedure find_all_book()
begin
    select id, title, author, category, stock from book;
end //

drop procedure if exists add_book;
create procedure add_book(
    in_title varchar(150),
    in_author varchar(150),
    in_category varchar(150),
    in_stock int
)
begin
    insert into book(title, author, category, stock)
        values(in_title, in_author, in_category, in_stock);
end //

drop procedure if exists find_book_by_title;
create procedure find_book_by_title(
    in_name varchar(150)
)
begin
    select id, title, author, category, stock from book
    where title like concat('%', 'in_name', '%');
end //

drop procedure if exists find_book_by_id;
create procedure find_book_by_id (in_id int)
begin
    select id, title, author, category, stock from book
        where id = in_id;
end //

drop procedure if exists update_book;
create procedure update_book(
    in_id int,
    in_title varchar(150),
    in_author varchar(150),
    in_category varchar(150),
    in_stock int
)
begin
    update book
        set title = in_title,
            author = in_author,
            category = in_category,
            stock = in_stock
    where id = in_id;
end //

drop procedure if exists delete_book;
create procedure delete_book(in_id int)
begin
    delete from book
           where id = in_id;
end //

delimiter ;