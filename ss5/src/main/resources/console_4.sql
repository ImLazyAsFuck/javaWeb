use ss5;

create table task(
    id int primary key auto_increment,
    name varchar(150) not null unique,
    completed bit default(0),
    dueDate date default(curdate())
);

delimiter //
drop procedure if exists get_all_task;
create procedure get_all_task()
begin
    select id, name, completed, dueDate from task;
end //
delimiter ;

delimiter //
drop procedure if exists save_task;
create procedure save_task(
    in_name varchar(150)
)
begin
    insert into task(name) values (in_name);
end //
delimiter ;

delimiter //
drop procedure if exists update_task;
create procedure update_task(
    in_id int,
    in_name varchar(150),
    in_completed bit
)
begin
    update task
        set name = in_name, completed = in_completed
    where id = in_id;
end //
delimiter ;

delimiter //
drop procedure if exists delete_task;
create procedure delete_task(in_id int)
begin
    delete from task
    where id = in_id;
end //
delimiter ;