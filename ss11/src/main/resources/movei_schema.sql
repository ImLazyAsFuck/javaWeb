CREATE DATABASE IF NOT EXISTS SS11;
USE SS11;

DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       director VARCHAR(50) NOT NULL,
                       releaseDate DATE NOT NULL,
                       genre VARCHAR(30) NOT NULL,
                       poster VARCHAR(255) NOT NULL
);

DELIMITER $$

DROP PROCEDURE IF EXISTS add_movie;
CREATE PROCEDURE add_movie (
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(50),
    IN p_releaseDate DATE,
    IN p_genre VARCHAR(30),
    IN p_poster VARCHAR(255)
)
BEGIN
    INSERT INTO movie (title, director, releaseDate, genre, poster)
    VALUES (p_title, p_director, p_releaseDate, p_genre, p_poster);
END $$

DROP PROCEDURE IF EXISTS update_movie;
CREATE PROCEDURE update_movie (
    IN p_id INT,
    IN p_title VARCHAR(100),
    IN p_director VARCHAR(50),
    IN p_releaseDate DATE,
    IN p_genre VARCHAR(30),
    IN p_poster VARCHAR(255)
)
BEGIN
    UPDATE movie
    SET title = p_title,
        director = p_director,
        releaseDate = p_releaseDate,
        genre = p_genre,
        poster = p_poster
    WHERE id = p_id;
END $$

DROP PROCEDURE IF EXISTS delete_movie;
CREATE PROCEDURE delete_movie (
    IN p_id INT
)
BEGIN
    DELETE FROM movie WHERE id = p_id;
END $$

DROP PROCEDURE IF EXISTS get_all_movies;
CREATE PROCEDURE get_all_movies()
BEGIN
    SELECT id, title, director, releaseDate, genre, poster FROM movie ORDER BY releaseDate DESC;
END $$

DROP PROCEDURE IF EXISTS get_movie_by_id;
CREATE PROCEDURE get_movie_by_id (
    IN p_id INT
)
BEGIN
    SELECT id, title, director, releaseDate, genre, poster
    FROM movie
    WHERE id = p_id;
END $$

DELIMITER ;