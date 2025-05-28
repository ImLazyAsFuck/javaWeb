CREATE DATABASE IF NOT EXISTS ss14;
USE ss14;

CREATE TABLE IF NOT EXISTS categories_vi (
                                             id INT PRIMARY KEY AUTO_INCREMENT,
                                             categoryName VARCHAR(255),
                                             description TEXT
);

CREATE TABLE IF NOT EXISTS categories_en (
                                             id INT PRIMARY KEY AUTO_INCREMENT,
                                             categoryName VARCHAR(255),
                                             description TEXT
);

DELIMITER //

CREATE PROCEDURE save_categories_vi (
    IN p_categoryName VARCHAR(255),
    IN p_description TEXT
)
BEGIN
    INSERT INTO categories_vi (categoryName, description)
    VALUES (p_categoryName, p_description);
END //

CREATE PROCEDURE find_all_categories_vi ()
BEGIN
    SELECT * FROM categories_vi;
END //

CREATE PROCEDURE save_categories_en (
    IN p_categoryName VARCHAR(255),
    IN p_description TEXT
)
BEGIN
    INSERT INTO categories_en (categoryName, description)
    VALUES (p_categoryName, p_description);
END //

CREATE PROCEDURE find_all_categories_en ()
BEGIN
    SELECT * FROM categories_en;
END //

DELIMITER ;