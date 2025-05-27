create database if not exists ss12;
use ss12;

drop table if exists seat;
drop table if exists bus;

create table bus (
                     id int auto_increment primary key,
                     license_plate varchar(150) not null,
                     bus_type enum('VIP', 'LUXURY', 'NORMAL') not null,
                     row_seat int not null check(row_seat > 0),
                     col_seat int not null check(col_seat > 0),
                     total_seat int not null,
                     image varchar(150) not null
);

create table seat (
                      id int auto_increment primary key,
                      name_seat varchar(150) not null,
                      price decimal(15,2) check (price > 0),
                      bus_id int not null,
                      status enum('AVAILABLE', 'UNAVAILABLE') not null default 'AVAILABLE',
                      foreign key (bus_id) references bus(id) on delete cascade
);

delimiter //
create trigger calc_total_seat_before_insert
    before insert on bus
    for each row
begin
    set new.total_seat = new.row_seat * new.col_seat;
end //

create trigger calc_total_seat_before_update
    before update on bus
    for each row
begin
    set new.total_seat = new.row_seat * new.col_seat;
end //
delimiter ;

delimiter //
drop procedure if exists add_bus;
create procedure add_bus(
    in p_license_plate varchar(150),
    in p_bus_type enum('VIP', 'LUXURY', 'NORMAL'),
    in p_row_seat int,
    in p_col_seat int,
    in p_image varchar(150)
)
begin
    declare v_bus_id int;
    declare v_row int default 1;
    declare v_col int default 1;
    declare v_seat_name varchar(150);
    declare v_price decimal(15,2);

    set v_price = case p_bus_type
                      when 'NORMAL' then 100000
                      when 'VIP' then 150000
                      when 'LUXURY' then 200000
                      else 100000
        end;

    insert into bus (license_plate, bus_type, row_seat, col_seat, image)
    values (p_license_plate, p_bus_type, p_row_seat, p_col_seat, p_image);

    set v_bus_id = last_insert_id();

    while v_row <= p_row_seat do
            set v_col = 1;
            while v_col <= p_col_seat do
                    set v_seat_name = concat(char(64 + v_row), v_col);
                    insert into seat (name_seat, price, bus_id, status)
                    values (v_seat_name, v_price, v_bus_id, 'AVAILABLE');
                    set v_col = v_col + 1;
                end while;
            set v_row = v_row + 1;
        end while;
end //
delimiter ;

delimiter //
drop procedure if exists update_bus;
create procedure update_bus(
    in p_id int,
    in p_license_plate varchar(150),
    in p_bus_type enum('VIP', 'LUXURY', 'NORMAL'),
    in p_row_seat int,
    in p_col_seat int,
    in p_image varchar(150)
)
begin
    declare v_price decimal(15,2);

    set v_price = case p_bus_type
                      when 'NORMAL' then 100000
                      when 'VIP' then 150000
                      when 'LUXURY' then 200000
                      else 100000
        end;

    delete from seat where bus_id = p_id;

    update bus
    set license_plate = p_license_plate,
        bus_type = p_bus_type,
        row_seat = p_row_seat,
        col_seat = p_col_seat,
        image = p_image
    where id = p_id;

    begin
        declare v_row int default 1;
        declare v_col int default 1;
        declare v_seat_name varchar(150);

        while v_row <= p_row_seat do
                set v_col = 1;
                while v_col <= p_col_seat do
                        set v_seat_name = concat(char(64 + v_row), v_col);
                        insert into seat (name_seat, price, bus_id, status)
                        values (v_seat_name, v_price, p_id, 'AVAILABLE');
                        set v_col = v_col + 1;
                    end while;
                set v_row = v_row + 1;
            end while;
    end;
end //
delimiter ;

delimiter //
drop procedure if exists delete_bus;
create procedure delete_bus(
    in p_id int
)
begin
    delete from bus where id = p_id;
end //
delimiter ;

delimiter //
drop procedure if exists list_buses;
create procedure list_buses()
begin
    select id, license_plate, bus_type, row_seat, col_seat, total_seat, image
    from bus;
end //
delimiter ;

delimiter //
drop procedure if exists show_bus_seats;
create procedure show_bus_seats(
    in p_bus_id int
)
begin
    select
        name_seat,
        price,
        status
    from seat
    where bus_id = p_bus_id
    order by name_seat;
end //
delimiter ;