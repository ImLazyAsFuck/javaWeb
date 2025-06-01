create database if not exists cv_management;
use cv_management;

drop table if exists cv;
create table cv(
                   id int auto_increment primary key,
                   fullname varchar(150) not null,
                   email varchar(150) not null unique,
                   phone varchar(20) not null unique,
                   education varchar(150) not null,
                   experience varchar(255) not null,
                   skills varchar(255) not null,
                   image varchar(255) not null
);

create database if not exists cv_management;
use cv_management;

create table if not exists cv (
                                  id int auto_increment primary key,
                                  fullname varchar(150) not null,
                                  email varchar(150) not null unique,
                                  phone varchar(20) not null unique,
                                  education varchar(150) not null,
                                  experience varchar(255) not null,
                                  skills varchar(255) not null,
                                  image varchar(255)
);

delimiter //

drop procedure if exists add_cv;
create procedure add_cv (
    in p_fullname varchar(150),
    in p_email varchar(150),
    in p_phone varchar(20),
    in p_education varchar(150),
    in p_experience varchar(255),
    in p_skills varchar(255),
    in p_image varchar(255)
)
begin
    insert into cv (fullname, email, phone, education, experience, skills, image)
    values (p_fullname, p_email, p_phone, p_education, p_experience, p_skills, p_image);
end //

drop procedure if exists update_cv;
create procedure update_cv (
    in p_id int,
    in p_fullname varchar(150),
    in p_email varchar(150),
    in p_phone varchar(20),
    in p_education varchar(150),
    in p_experience varchar(255),
    in p_skills varchar(255),
    in p_image varchar(255)
)
begin
    update cv
    set fullname = p_fullname,
        email = p_email,
        phone = p_phone,
        education = p_education,
        experience = p_experience,
        skills = p_skills,
        image = p_image
    where id = p_id;
end //

drop procedure if exists delete_cv;
create procedure delete_cv (
    in p_id int
)
begin
    delete from cv where id = p_id;
end //

drop procedure if exists get_cv_by_id;
create procedure get_cv_by_id (
    in p_id int
)
begin
    select * from cv where id = p_id;
end //

drop procedure if exists get_all_cvs;
create procedure get_all_cvs ()
begin
    select * from cv;
end //

drop procedure if exists unique_email;
create procedure unique_email(p_email varchar(255))
begin
    select count(cv.id) from cv where email = p_email;
end //

drop procedure if exists unique_phone;
create procedure unique_phone(p_phone varchar(20))
begin
    select count(cv.id) from cv where phone = p_phone;
end //

drop procedure if exists search_by_name;
create procedure search_by_name(p_fullname varchar(150))
begin
    select
        id,
        fullname,
        email,
        phone,
        education,
        experience,
        skills,
        image
    from cv
    where fullname like concat('%', p_fullname, '%');
end //

delimiter ;
