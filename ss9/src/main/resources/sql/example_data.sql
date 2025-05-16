use ss9;

insert into customer (c_username, c_password, c_phone, c_address, c_gender) values
                                                                                ('tanjiro_k', 'nezuko123', '0901111222', 'Kimetsu Town', 'MALE'),
                                                                                ('mikasa_a', 'shiganshina', '0902222333', 'Shiganshina District', 'FEMALE'),
                                                                                ('goku_s', 'kamehameha', '0903333444', 'Planet Vegeta', 'MALE'),
                                                                                ('luffy_d', 'meatlover', '0904444555', 'East Blue', 'MALE'),
                                                                                ('nami_n', 'mapthief', '0905555666', 'Cocoyashi Village', 'FEMALE'),
                                                                                ('naruto_u', 'dattebayo', '0906666777', 'Konoha', 'MALE'),
                                                                                ('hinata_h', 'byakugan', '0907777888', 'Hyuga Clan', 'FEMALE'),
                                                                                ('eren_y', 'titanrage', '0908888999', 'Paradise Island', 'MALE'),
                                                                                ('aqua_g', 'useless123', '0909999000', 'Axel Town', 'FEMALE'),
                                                                                ('kazuma_s', 'explosion', '0910000111', 'Fantasy Isekai', 'MALE');

insert into movie (m_title, m_director, m_genre, m_description, m_duration, m_language) values
                                                                                            ('Demon Slayer: Mugen Train', 'Haruo Sotozaki', 'Action', 'Tanjiro faces the Flame Hashira and a demon on the Infinity Train.', 117, 'Japanese'),
                                                                                            ('Attack on Titan: Final Season', 'Yuichiro Hayashi', 'Drama', 'Eren leads the charge in the final battle against Marley.', 138, 'Japanese'),
                                                                                            ('Dragon Ball Super: Broly', 'Tatsuya Nagamine', 'Action', 'Goku and Vegeta face off against the legendary Saiyan Broly.', 101, 'Japanese'),
                                                                                            ('One Piece: Stampede', 'Takashi Otsuka', 'Adventure', 'Pirates gather for the biggest treasure hunt of the era.', 101, 'Japanese'),
                                                                                            ('Naruto Shippuden: The Last', 'Tsuneo Kobayashi', 'Romance', 'Naruto and Hinata face a new threat from the moon.', 112, 'Japanese'),
                                                                                            ('My Hero Academia: Heroes Rising', 'Kenji Nagasaki', 'Action', 'Class 1-A faces a powerful villain on a remote island.', 104, 'Japanese'),
                                                                                            ('Fate/stay night: Heaven’s Feel III', 'Tomonori Sudou', 'Fantasy', 'The final battle of the Holy Grail War begins.', 120, 'Japanese'),
                                                                                            ('Your Name', 'Makoto Shinkai', 'Romance', 'Two strangers switch bodies and try to meet.', 112, 'Japanese'),
                                                                                            ('Weathering with You', 'Makoto Shinkai', 'Fantasy', 'A boy meets a girl who can stop the rain.', 111, 'Japanese'),
                                                                                            ('A Silent Voice', 'Naoko Yamada', 'Drama', 'A bully tries to make amends with a deaf girl.', 130, 'Japanese');

insert into screen_room (sr_name, sr_total_seats) values
                                                      ('Neo Tokyo Room', 100),
                                                      ('Konoha Room', 80),
                                                      ('Titan Hall', 120),
                                                      ('Spirited Theater', 90),
                                                      ('Alchemist Room', 75),
                                                      ('Ainz Chamber', 60),
                                                      ('Akihabara Dome', 110),
                                                      ('U.A. Hero Room', 85),
                                                      ('Ghibli Theater', 100),
                                                      ('Red Line Cinema', 95);

insert into schedule (m_id, sc_show_time, sr_id, sc_available_seats, sc_format) values
                                                                                    (1, '2025-05-20', 1, 100, 'TWO_D'),
                                                                                    (2, '2025-05-21', 2, 80, 'THREE_D'),
                                                                                    (3, '2025-05-22', 3, 120, 'TWO_D'),
                                                                                    (4, '2025-05-23', 4, 90, 'THREE_D'),
                                                                                    (5, '2025-05-24', 5, 75, 'TWO_D'),
                                                                                    (6, '2025-05-25', 6, 60, 'TWO_D'),
                                                                                    (7, '2025-05-26', 7, 110, 'THREE_D'),
                                                                                    (8, '2025-05-27', 8, 85, 'TWO_D'),
                                                                                    (9, '2025-05-28', 9, 100, 'THREE_D'),
                                                                                    (10, '2025-05-29', 10, 95, 'TWO_D');

insert into seat (sr_id, s_price, s_status) values
(1, 50000, 0), (1, 50000, 0), (1, 50000, 0), (1, 50000, 0), (1, 50000, 0),
(2, 55000, 0), (2, 55000, 0), (2, 55000, 0), (2, 55000, 0), (2, 55000, 0),
(3, 60000, 0), (3, 60000, 0), (3, 60000, 0), (3, 60000, 0), (3, 60000, 0),
(4, 65000, 0), (4, 65000, 0), (4, 65000, 0), (4, 65000, 0), (4, 65000, 0),
(5, 70000, 0), (5, 70000, 0), (5, 70000, 0), (5, 70000, 0), (5, 70000, 0),
(6, 75000, 0), (6, 75000, 0), (6, 75000, 0), (6, 75000, 0), (6, 75000, 0),
(7, 80000, 0), (7, 80000, 0), (7, 80000, 0), (7, 80000, 0), (7, 80000, 0),
(8, 85000, 0), (8, 85000, 0), (8, 85000, 0), (8, 85000, 0), (8, 85000, 0),
(9, 90000, 0), (9, 90000, 0), (9, 90000, 0), (9, 90000, 0), (9, 90000, 0),
(10, 95000, 0), (10, 95000, 0), (10, 95000, 0), (10, 95000, 0), (10, 95000, 0);
