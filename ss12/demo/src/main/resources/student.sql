create database if not exists ss12;
use ss12;

drop table if exists student;
create table student(
    id int auto_increment primary key,
    name varchar(150) not null,
    email varchar(150) not null,
    dob date default(now())
);

delimiter //

drop procedure if exists add_student;
create procedure add_student (
    in p_name varchar(150),
    in p_email varchar(150),
    in p_dob date
)
begin
    insert into student(name, email, dob)
    values (p_name, p_email, p_dob);
end //

drop procedure if exists update_student;
create procedure update_student (
    in p_id int,
    in p_name varchar(150),
    in p_email varchar(150),
    in p_dob date
)
begin
    update student
    set name = p_name,
        email = p_email,
        dob = p_dob
    where id = p_id;
end //

drop procedure if exists delete_student;
create procedure delete_student (
    in p_id int
)
begin
    delete from student where id = p_id;
end //

drop procedure if exists get_all_students;
create procedure get_all_students()
begin
    select id, name, email, dob from student;
end //

drop procedure if exists get_student_by_id;
create procedure get_student_by_id(p_id int)
begin
    select id, name, email, dob from student
                                where id = p_id;
end //

delimiter ;