USE SS11;

DROP PROCEDURE IF EXISTS sp_get_all_categories;
DROP PROCEDURE IF EXISTS sp_get_category_by_id;
DROP PROCEDURE IF EXISTS sp_get_category_by_name;
DROP PROCEDURE IF EXISTS sp_insert_category;
DROP PROCEDURE IF EXISTS sp_update_category;
DROP PROCEDURE IF EXISTS sp_delete_category;
DROP PROCEDURE IF EXISTS sp_check_category_name_exists;
DROP PROCEDURE IF EXISTS sp_check_category_name_exists_except_id;

DELIMITER $$
CREATE PROCEDURE sp_get_all_categories()
BEGIN
    SELECT * FROM category;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_category_by_id(IN p_id INT)
BEGIN
    SELECT * FROM category WHERE id = p_id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_category_by_name(IN p_name VARCHAR(50))
BEGIN
    SELECT * FROM category WHERE categoryName = p_name;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_insert_category(
    IN p_categoryName VARCHAR(50),
    IN p_status BIT,
    OUT p_id INT
)
BEGIN
    INSERT INTO category (categoryName, status) VALUES (p_categoryName, p_status);
    SET p_id = LAST_INSERT_ID();
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_update_category(
    IN p_id INT,
    IN p_categoryName VARCHAR(50),
    IN p_status BIT
)
BEGIN
    UPDATE category SET categoryName = p_categoryName, status = p_status WHERE id = p_id;
    SELECT ROW_COUNT() AS affected_rows;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_delete_category(IN p_id INT)
BEGIN
    DELETE FROM category WHERE id = p_id;
    SELECT ROW_COUNT() AS affected_rows;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_check_category_name_exists(
    IN p_name VARCHAR(50),
    OUT p_exists BIT
)
BEGIN
    DECLARE count_val INT;
    SELECT COUNT(*) INTO count_val FROM category WHERE categoryName = p_name;
    SET p_exists = IF(count_val > 0, 1, 0);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_check_category_name_exists_except_id(
    IN p_name VARCHAR(50),
    IN p_id INT,
    OUT p_exists BIT
)
BEGIN
    DECLARE count_val INT;
    SELECT COUNT(*) INTO count_val FROM category WHERE categoryName = p_name AND id != p_id;
    SET p_exists = IF(count_val > 0, 1, 0);
END$$
DELIMITER ;
