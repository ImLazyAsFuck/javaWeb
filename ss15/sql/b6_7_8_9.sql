-- Creating the database
CREATE DATABASE IF NOT EXISTS ss15;
USE ss15;

-- Creating the b6products table
CREATE TABLE IF NOT EXISTS b6products (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
                                          price DOUBLE NOT NULL,
                                          description TEXT
);

-- Creating the reviews table
CREATE TABLE IF NOT EXISTS reviews (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       product_id INT NOT NULL,
                                       user_id INT NOT NULL,
                                       rating INT NOT NULL,
                                       comment TEXT,
                                       FOREIGN KEY (product_id) REFERENCES b6products(id)
);

-- Creating the cart table
CREATE TABLE IF NOT EXISTS cart (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    user_id INT NOT NULL,
                                    product_id INT NOT NULL,
                                    quantity INT NOT NULL,
                                    FOREIGN KEY (product_id) REFERENCES b6products(id)
);

-- Creating the orders table
CREATE TABLE IF NOT EXISTS orders (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      user_id INT NOT NULL,
                                      recipient_name VARCHAR(255) NOT NULL,
                                      address TEXT NOT NULL,
                                      phone_number VARCHAR(20) NOT NULL
);

-- Creating the order_details table
CREATE TABLE IF NOT EXISTS order_details (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             order_id INT NOT NULL,
                                             product_id INT NOT NULL,
                                             quantity INT NOT NULL,
                                             current_price DOUBLE NOT NULL,
                                             FOREIGN KEY (order_id) REFERENCES orders(id),
                                             FOREIGN KEY (product_id) REFERENCES b6products(id)
);

-- Stored procedure to save a product review
DELIMITER //
CREATE PROCEDURE saveProductReview(
    IN p_product_id INT,
    IN p_rating INT,
    IN p_comment TEXT
)
BEGIN
    INSERT INTO reviews (product_id, user_id, rating, comment)
    VALUES (p_product_id, 1, p_rating, p_comment); -- Assuming user_id = 1 for simplicity; adjust as needed
END //
DELIMITER ;

-- Stored procedure to get all products
DELIMITER //
CREATE PROCEDURE getAllProducts()
BEGIN
    SELECT id, name, price, description
    FROM b6products;
END //
DELIMITER ;

-- Stored procedure to get reviews by product ID
DELIMITER //
CREATE PROCEDURE getAllReviewsByProductId(
    IN p_product_id INT
)
BEGIN
    SELECT id, product_id, user_id, rating, comment
    FROM reviews
    WHERE product_id = p_product_id;
END //
DELIMITER ;

-- Stored procedure to get a product by ID
DELIMITER //
CREATE PROCEDURE getProductById(
    IN p_id INT
)
BEGIN
    SELECT id, name, price, description
    FROM b6products
    WHERE id = p_id;
END //
DELIMITER ;

-- Stored procedure to add to cart
DELIMITER //
CREATE PROCEDURE addToCart(
    IN p_product_id INT,
    IN p_quantity INT
)
BEGIN
    INSERT INTO cart (user_id, product_id, quantity)
    VALUES (1, p_product_id, p_quantity); -- Assuming user_id = 1 for simplicity; adjust as needed
END //
DELIMITER ;

-- Stored procedure to get cart by user ID
DELIMITER //
CREATE PROCEDURE getCartByUserId(
    IN p_user_id INT
)
BEGIN
    SELECT id, user_id, product_id, quantity
    FROM cart
    WHERE user_id = p_user_id;
END //
DELIMITER ;

-- Stored procedure to add an order
DELIMITER //
CREATE PROCEDURE addOrder(
    IN p_user_id INT,
    IN p_recipient_name VARCHAR(255),
    IN p_address TEXT,
    IN p_phone_number VARCHAR(20)
)
BEGIN
    INSERT INTO orders (user_id, recipient_name, address, phone_number)
    VALUES (p_user_id, p_recipient_name, p_address, p_phone_number);
END //
DELIMITER ;

INSERT INTO b6products (name, price, description) VALUES
                                                      ('Tai nghe không dây', 49.99, 'Tai nghe Bluetooth chất lượng cao với âm thanh sống động.'),
                                                      ('Bàn phím cơ', 89.99, 'Bàn phím cơ RGB với switch Cherry MX, siêu bền.'),
                                                      ('Chuột không dây', 29.99, 'Chuột không dây siêu nhạy, pin dùng được 6 tháng.'),
                                                      ('Balo laptop', 39.99, 'Balo chống sốc, chống nước, phù hợp cho laptop 15 inch.'),
                                                      ('Loa Bluetooth', 59.99, 'Loa di động với âm thanh 360 độ, thời lượng pin 12 giờ.');