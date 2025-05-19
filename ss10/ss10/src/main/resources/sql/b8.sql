create database if not exists ss10;
use ss10;

DROP TABLE IF EXISTS Seat;
CREATE TABLE Seat (
                      seat_id INT AUTO_INCREMENT PRIMARY KEY,
                      seat_number VARCHAR(10) NOT NULL
);

CREATE TABLE Ticket (
                        ticket_id INT AUTO_INCREMENT PRIMARY KEY,
                        movie_title VARCHAR(255) NOT NULL,
                        show_time DATETIME NOT NULL,
                        total_amount DOUBLE NOT NULL
);


CREATE TABLE Ticket_Seat (
                             ticket_id INT NOT NULL,
                             seat_id INT NOT NULL,
                             PRIMARY KEY(ticket_id, seat_id),
                             FOREIGN KEY (ticket_id) REFERENCES Ticket(ticket_id) ON DELETE CASCADE,
                             FOREIGN KEY (seat_id) REFERENCES Seat(seat_id) ON DELETE CASCADE
);

DELIMITER $$

CREATE PROCEDURE BookTicket (
    IN p_movieTitle VARCHAR(255),
    IN p_showTime DATETIME,
    IN p_totalAmount DOUBLE
)
BEGIN
    -- Thêm vé mới vào bảng Ticket
    INSERT INTO Ticket (movie_title, show_time, total_amount)
    VALUES (p_movieTitle, p_showTime, p_totalAmount);

    -- Có thể thêm logic khác ở đây, ví dụ lấy ticket_id mới chèn
    -- SET @last_ticket_id = LAST_INSERT_ID();
END $$

DELIMITER ;
