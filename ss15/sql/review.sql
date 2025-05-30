create database if not exists ss15;
use ss15;

drop table if exists Review;
create table Review(
                       id int auto_increment primary key,
                       user_id int not null,
                       product_id int not null,
                       rating int check ( rating >= 0 ) check(rating <= 5),
                       comment text,
                       unique(user_id, product_id)
);


delimiter //

drop procedure if exists save_review;
create procedure save_review(
    p_u_id int,
    p_p_id int,
    p_rating int,
    p_comment text
)
begin
    declare countId int;
    select count(id) into countId
    from Review
    where user_id = p_u_id and product_id = p_p_id;

    if countId > 0 then
        update Review
        set rating = p_rating,
            comment = p_comment
        where user_id = p_u_id and product_id = p_p_id;
    else
        insert into Review(user_id, product_id, rating, comment)
        values(p_u_id, p_p_id, p_rating, p_comment);
    end if;
end //

drop procedure if exists find_review_by_id;
create procedure find_review_by_id(p_p_id int)
begin
    select id, user_id, product_id, rating, comment from Review
        where product_id = p_p_id;
end //

delimiter ;