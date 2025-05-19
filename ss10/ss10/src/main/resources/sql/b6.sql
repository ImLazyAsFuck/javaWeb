create database if not exists ss10;
use ss10;

drop table if exists doc;
create table doc(
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
    insert into doc(file, description)
        values(in_file, in_description);
end //

drop procedure if exists get_all_doc;
create procedure get_all_doc()
begin
    select id, file, description from doc;
end //

create procedure get_doc_by_id(in_id int)
begin
    select id, file, description from doc
        where id = in_id;
end //

delimiter ;