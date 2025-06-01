create database if not exists ss16;
use ss16;

CREATE TABLE ticket (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT NOT NULL,
                        trip_bus_id INT NOT NULL,
                        list_seat VARCHAR(255) NOT NULL,
                        total_money DECIMAL(15,2) NOT NULL,
                        departure_date DATE NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES user(id),
                        FOREIGN KEY (trip_bus_id) REFERENCES bus_trip(id)
);

DELIMITER $$
CREATE PROCEDURE insert_ticket(
    IN p_user_id INT,
    IN p_trip_bus_id INT,
    IN p_list_seat VARCHAR(255),
    IN p_total_money DECIMAL(15,2),
    IN p_departure_date DATE
)
BEGIN
    INSERT INTO ticket(user_id, trip_bus_id, list_seat, total_money, departure_date)
    VALUES (p_user_id, p_trip_bus_id, p_list_seat, p_total_money, p_departure_date);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_tickets_by_user(IN p_user_id INT)
BEGIN
    SELECT * FROM ticket WHERE user_id = p_user_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_ticket_by_id(IN p_id INT)
BEGIN
    SELECT * FROM ticket WHERE id = p_id;
END$$
DELIMITER ;