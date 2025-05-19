create database if not exists ss10;
use ss10;

drop table if exists project;

create table project (
                         id int primary key auto_increment,
                         name varchar(150) not null,
                         description varchar(255),
                         file_url varchar(255) not null
);

delimiter //

drop procedure if exists add_project;
create procedure add_project(
    in in_name varchar(150),
    in in_description varchar(255),
    in in_file_url varchar(255)
)
begin
    insert into project(name, description, file_url)
    values (in_name, in_description, in_file_url);
end //

drop procedure if exists get_all_projects;
create procedure get_all_projects()
begin
    select id, name, description, file_url from project;
end //

drop procedure if exists get_project_by_id;
create procedure get_project_by_id(
    in in_id int
)
begin
    select * from project where id = in_id;
end //

drop procedure if exists delete_project_by_id;
create procedure delete_project_by_id(
    in in_id int
)
begin
    delete from project where id = in_id;
end //

drop procedure if exists update_project;
create procedure update_project(
    in in_id int,
    in in_name varchar(150),
    in in_description varchar(255),
    in in_file_url varchar(255)
)
begin
    update project
    set name = in_name,
        description = in_description,
        file_url = in_file_url
    where id = in_id;
end //

delimiter ;
