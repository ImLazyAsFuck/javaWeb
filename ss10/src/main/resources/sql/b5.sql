create database if not exists ss10;
use ss10;

drop table if exists document;
create table document(
    id int auto_increment primary key,
    title varchar(150) not null unique,
    description varchar(255),
    file varchar(255) not null
);

delimiter //

drop procedure if exists find_all_doc;
create procedure find_all_doc()
begin
    select id, title, description, file from document;
end //

drop procedure if exists save;
create procedure save(
    in_title varchar(150),
    in_description varchar(255),
    in_file varchar(255)
)
begin
    insert into document(title, description, file)
        values(in_title, in_description, in_file);
end //

drop procedure if exists find_doc_by_id;
create procedure find_doc_by_id(in_id int)
begin
    select id, title, description, file from document;
end //

drop procedure if exists find_doc_by_title;
create procedure find_doc_by_title(in_title varchar(150))
begin
    select id, title, description, file from document where title = in_title;
end //

delimiter ;