create database if not exists ss16;
use ss16;

drop table if exists ticket;
drop table if exists bus_trip;
CREATE TABLE bus_trip (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          departure_point VARCHAR(100) NOT NULL,
                          destination VARCHAR(100) NOT NULL,
                          departure_time DATETIME NOT NULL,
                          arrival_time DATETIME NOT NULL,
                          bus_id INT NOT NULL,
                          seats_available INT NOT NULL,
                          image VARCHAR(255),
                          FOREIGN KEY (bus_id) REFERENCES bus(id)
);



DELIMITER $$

drop procedure if exists insert_bustrip;
CREATE PROCEDURE insert_bustrip(
    IN p_departure_point VARCHAR(100),
    IN p_destination VARCHAR(100),
    IN p_departure_time DATETIME,
    IN p_arrival_time DATETIME,
    IN p_bus_id INT,
    IN p_seats_available INT,
    IN p_image VARCHAR(255)
)
BEGIN
    INSERT INTO bus_trip(departure_point, destination, departure_time, arrival_time, bus_id, seats_available, image)
    VALUES (p_departure_point, p_destination, p_departure_time, p_arrival_time, p_bus_id, p_seats_available, p_image);
END$$
DELIMITER ;

DELIMITER $$
drop procedure if exists get_all_bustrip;
CREATE PROCEDURE get_all_bustrip()
BEGIN
    SELECT id, departure_point, destination, departure_time, arrival_time, bus_id, seats_available, image FROM bus_trip;
END$$
DELIMITER ;

DELIMITER $$
drop procedure if exists get_bustrip_by_id;
CREATE PROCEDURE get_bustrip_by_id(IN p_id INT)
BEGIN
    SELECT id, departure_point, destination, departure_time, arrival_time, bus_id, seats_available, image FROM bus_trip WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
drop procedure if exists update_bustrip;
CREATE PROCEDURE update_bustrip(
    IN p_id INT,
    IN p_departure_point VARCHAR(100),
    IN p_destination VARCHAR(100),
    IN p_departure_time DATETIME,
    IN p_arrival_time DATETIME,
    IN p_bus_id INT,
    IN p_seats_available INT,
    IN p_image VARCHAR(255)
)
BEGIN
    UPDATE bus_trip SET departure_point = p_departure_point, destination = p_destination, departure_time = p_departure_time,
                        arrival_time = p_arrival_time, bus_id = p_bus_id, seats_available = p_seats_available, image = p_image
    WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
drop procedure if exists delete_bustrip;
CREATE PROCEDURE delete_bustrip(IN p_id INT)
BEGIN
    DELETE FROM bus_trip WHERE id = p_id;
END$$
DELIMITER ;
