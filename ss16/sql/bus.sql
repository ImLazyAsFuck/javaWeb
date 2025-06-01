create database if not exists ss16;
use ss16;

drop table if exists seat;
drop table if exists bus;

create table bus (
                     id int auto_increment primary key,
                     licenseplate varchar(8) not null unique,
                     bus_type enum('VIP', 'LUXURY', 'NORMAL') default 'NORMAL',
                     row_seat int not null,
                     col_seat int not null,
                     total_seat int not null,
                     image varchar(255),
                     check (row_seat > 0 and col_seat > 0)
);

create table seat (
                      id int auto_increment primary key,
                      name_seat varchar(10) not null,
                      price decimal(15,2) not null,
                      status bit default 1,
                      bus_id int not null,
                      foreign key (bus_id) references bus(id) on delete cascade
);

delimiter //

drop procedure if exists insert_bus//
create procedure insert_bus(
    in p_licenseplate varchar(50),
    in p_bus_type enum('VIP', 'LUXURY', 'NORMAL'),
    in p_row_seat int,
    in p_col_seat int,
    in p_image varchar(255)
)
begin
    declare v_total_seat int;
    declare v_bus_id int;
    declare exit handler for sqlexception
        begin
            rollback;
            resignal;
        end;

    start transaction;

    set v_total_seat = p_row_seat * p_col_seat;

    insert into bus (licenseplate, bus_type, row_seat, col_seat, total_seat, image)
    values (p_licenseplate, p_bus_type, p_row_seat, p_col_seat, v_total_seat, p_image);

    set v_bus_id = last_insert_id();
    call create_seats_for_bus(v_bus_id, p_bus_type, p_row_seat, p_col_seat);

    commit;
end//

drop procedure if exists update_bus//
create procedure update_bus(
    in p_id int,
    in p_licenseplate varchar(50),
    in p_bus_type enum('VIP', 'LUXURY', 'NORMAL'),
    in p_row_seat int,
    in p_col_seat int,
    in p_image varchar(255)
)
begin
    declare v_total_seat int;
    declare exit handler for sqlexception
        begin
            rollback;
            resignal;
        end;

    start transaction;

    set v_total_seat = p_row_seat * p_col_seat;

    update bus
    set licenseplate = p_licenseplate,
        bus_type = p_bus_type,
        row_seat = p_row_seat,
        col_seat = p_col_seat,
        total_seat = v_total_seat,
        image = p_image
    where id = p_id;

    commit;
end//

drop procedure if exists delete_bus//
create procedure delete_bus(
    in p_id int
)
begin
    declare exit handler for sqlexception
        begin
            rollback;
            resignal;
        end;

    start transaction;

    delete from seat where bus_id = p_id;
    delete from bus where id = p_id;

    commit;
end//

drop procedure if exists get_bus_by_id//
create procedure get_bus_by_id(
    in p_id int
)
begin
    select id, licenseplate, bus_type, row_seat, col_seat, total_seat, image from bus where id = p_id;
end//

drop procedure if exists get_all_buses//
create procedure get_all_buses()
begin
    select id, licenseplate, bus_type, row_seat, col_seat, total_seat, image from bus;
end//

drop procedure if exists create_seats_for_bus;
CREATE PROCEDURE create_seats_for_bus (
    IN p_bus_id INT,
    IN p_bustype ENUM('VIP', 'LUXURY', 'NORMAL'),
    IN p_row_seat INT,
    IN p_col_seat INT
)
BEGIN
    DECLARE v_row INT DEFAULT 1;
    DECLARE v_col INT DEFAULT 1;
    DECLARE v_price DECIMAL(10,2);

    SET v_price = CASE p_bustype
                      WHEN 'NORMAL' THEN 100000
                      WHEN 'VIP' THEN 150000
                      WHEN 'LUXURY' THEN 200000
        END;

    WHILE v_row <= p_row_seat DO
            SET v_col = 1;
            WHILE v_col <= p_col_seat DO
                    INSERT INTO seat (name_seat, price, bus_id, status)
                    VALUES (
                               CONCAT(CHAR(64 + v_row), v_col),
                               v_price,
                               p_bus_id,
                               b'1'
                           );
                    SET v_col = v_col + 1;
                END WHILE;
            SET v_row = v_row + 1;
        END WHILE;
END;
//

drop procedure if exists unique_licenseplate;
create procedure unique_licenseplate(p_licenseplate varchar(8))
begin
    select count(id) from bus where licenseplate = p_licenseplate;
end //

delimiter ;

delimiter //

drop procedure if exists find_all_seat;
create procedure find_all_seat()
begin
    select id, name_seat, price, status, bus_id from seat;
end //


delimiter ;