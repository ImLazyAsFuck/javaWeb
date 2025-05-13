use ss6;

drop table if exists employees;

create table employees (
                           id int auto_increment primary key,
                           name varchar(100) not null,
                           birthday date not null,
                           phone varchar(15) not null,
                           email varchar(100) not null,
                           salary double not null,
                           position varchar(50) not null
);

delimiter //

drop procedure if exists find_all_employees//
create procedure find_all_employees()
begin
    select * from employees order by id;
end //

drop procedure if exists find_employee_by_id//
create procedure find_employee_by_id(in employee_id int)
begin
    select * from employees where id = employee_id;
end //

drop procedure if exists add_employee//
create procedure add_employee(
    in employee_name varchar(100),
    in employee_birthday date,
    in employee_phone varchar(15),
    in employee_email varchar(100),
    in employee_salary double,
    in employee_position varchar(50)
)
begin
    insert into employees(name, birthday, phone, email, salary, position)
    values (employee_name, employee_birthday, employee_phone, employee_email, employee_salary, employee_position);
end //

drop procedure if exists update_employee//
create procedure update_employee(
    in employee_id int,
    in employee_name varchar(100),
    in employee_birthday date,
    in employee_phone varchar(15),
    in employee_email varchar(100),
    in employee_salary double,
    in employee_position varchar(50)
)
begin
    update employees
    set name = employee_name,
        birthday = employee_birthday,
        phone = employee_phone,
        email = employee_email,
        salary = employee_salary,
        position = employee_position
    where id = employee_id;
end //

drop procedure if exists delete_employee//
create procedure delete_employee(in employee_id int)
begin
    delete from employees where id = employee_id;
end //

drop procedure if exists find_employees_by_name//
create procedure find_employees_by_name(in employee_name varchar(100))
begin
    select * from employees
    where name like concat('%', employee_name, '%')
    order by id;
end //

drop procedure if exists count_employees//
create procedure count_employees()
begin
    select count(*) as total from employees;
end //

drop procedure if exists find_employees_paged//
create procedure find_employees_paged(
    in offset_val int,
    in limit_val int,
    in sort_field varchar(50),
    in sort_direction varchar(4)
)
begin
    set @query = concat(
            'select * from employees order by ',
            sort_field, ' ',
            sort_direction,
            ' limit ?, ?'
                 );

    prepare stmt from @query;
    set @offset = offset_val;
    set @limit = limit_val;
    execute stmt using @offset, @limit;
    deallocate prepare stmt;
end //

delimiter ;
