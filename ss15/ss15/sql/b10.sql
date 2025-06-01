create database if not exists ss15;
use ss15;

CREATE TABLE cv (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    full_name VARCHAR(255) NOT NULL,
                    email VARCHAR(255) NOT NULL,
                    phone_number VARCHAR(20) NOT NULL,
                    education VARCHAR(255),
                    experience TEXT,
                    skills TEXT
);
DELIMITER $$
CREATE PROCEDURE insert_cv(
    IN p_full_name VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_phone_number VARCHAR(20),
    IN p_education VARCHAR(255),
    IN p_experience TEXT,
    IN p_skills TEXT
)
BEGIN
    INSERT INTO cv (full_name, email, phone_number, education, experience, skills)
    VALUES (p_full_name, p_email, p_phone_number, p_education, p_experience, p_skills);
END$$
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE update_cv(
    IN p_id BIGINT,
    IN p_full_name VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_phone_number VARCHAR(20),
    IN p_education VARCHAR(255),
    IN p_experience TEXT,
    IN p_skills TEXT
)
BEGIN
    UPDATE cv
    SET full_name = p_full_name,
        email = p_email,
        phone_number = p_phone_number,
        education = p_education,
        experience = p_experience,
        skills = p_skills
    WHERE id = p_id;
END$$
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE delete_cv(
    IN p_id BIGINT
)
BEGIN
    DELETE FROM cv WHERE id = p_id;
END$$
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE get_cv_by_id(
    IN p_id BIGINT
)
BEGIN
    SELECT * FROM cv WHERE id = p_id;
END$$
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE get_all_cvs()
BEGIN
    SELECT * FROM cv;
END$$
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE search_cv(
    IN p_keyword VARCHAR(255)
)
BEGIN
    SELECT * FROM cv
    WHERE full_name LIKE CONCAT('%', p_keyword, '%')
       OR email LIKE CONCAT('%', p_keyword, '%');
END$$
DELIMITER ;