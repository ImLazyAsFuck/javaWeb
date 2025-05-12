use ss5;

drop table if exists student;
create table student(
                        id int auto_increment primary key,
                        name varchar(150) not null,
                        age int check ( age > 0 ),
                        address varchar(255)
);

delimiter //
drop procedure if exists save_student;
create procedure save_student(
    in_name varchar(150),
    in_age int,
    in_address varchar(255)
)
begin
    insert into student(name, age, address)
    values(in_name, in_age, in_address);
end //
delimiter ;

delimiter //
drop procedure if exists get_all_student;
create procedure get_all_student()
begin
    select id, name, age, address from student;
end //
delimiter ;

delimiter //
drop procedure if exists get_student_by_page;
create procedure get_student_by_page(
    in_page int
)
begin
    declare offset_val int;
    set offset_val = (in_page - 1) * 5;

    select id, name, age, address
    from student
    limit 5 offset offset_val;
end //
delimiter ;

delimiter //
drop procedure if exists update_student;
create procedure update_student(
    in_id int,
    in_name varchar(150),
    in_age int,
    in_address varchar(255)
)
begin
    update student
    set name = in_name,
        age = in_age,
        address = in_address
    where id = in_id;
end //
delimiter ;

delimiter //
drop procedure if exists delete_student;
create procedure delete_student(
    in_id int
)
begin
    delete from student where id = in_id;
end //
delimiter ;

delimiter //
drop procedure if exists count_students;
create procedure count_students(
    out total_count int
)
begin
    select count(*) into total_count from student;
end //
delimiter ;

delimiter //
drop procedure if exists find_by_id;
create procedure find_by_id(in_id int)
begin
    select id, name, age, address from student where id = in_id;
end //
delimiter ;
