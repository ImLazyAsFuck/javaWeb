use ss9;

-- Thêm dữ liệu vào bảng screen_room
insert into screen_room (sr_name, sr_total_seats) values
                                                      ('Phòng Chiếu 1', 100),
                                                      ('Phòng Chiếu 2', 120),
                                                      ('Phòng Chiếu 3', 80),
                                                      ('Phòng Chiếu 4', 90),
                                                      ('Phòng Chiếu 5', 150);

-- Thêm dữ liệu vào bảng schedule
insert into schedule (sc_movie_title, sc_show_time, sr_id, sc_available_seats, sc_format) values
                                                                                              ('Avengers: Endgame', '2025-05-20', 1, 100, 'THREE_D'),
                                                                                              ('Your Name', '2025-05-21', 2, 120, 'TWO_D'),
                                                                                              ('Demon Slayer: Mugen Train', '2025-05-22', 3, 80, 'THREE_D'),
                                                                                              ('Suzume no Tojimari', '2025-05-23', 4, 90, 'TWO_D'),
                                                                                              ('Interstellar', '2025-05-24', 5, 150, 'TWO_D'),
                                                                                              ('Fate/stay night: Heaven''s Feel III', '2025-05-25', 1, 95, 'THREE_D'),
                                                                                              ('Jujutsu Kaisen 0', '2025-05-26', 2, 110, 'TWO_D'),
                                                                                              ('Weathering With You', '2025-05-27', 3, 75, 'TWO_D'),
                                                                                              ('The Matrix', '2025-05-28', 4, 85, 'THREE_D'),
                                                                                              ('Spirited Away', '2025-05-29', 5, 140, 'TWO_D');
