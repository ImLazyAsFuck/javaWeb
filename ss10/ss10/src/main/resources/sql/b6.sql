create database if not exists ss10;
use ss10;

drop table if exists file;
create table file(
    id int auto_increment primary key,
    file varchar(255) not null,
    description varchar(255)
);

delimiter //

drop procedure if exists save_doc;
create procedure save_doc(
    in_file varchar(255),
    in_description varchar(255)
)
begin
    insert into file(file, description)
        values(in_file, in_description);
end //

delimiter ;