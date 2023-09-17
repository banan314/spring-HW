insert into public."roles" ("id", "name")
values (1, 'ROLE_STUDENT');
insert into public."roles" ("id", "name")
values (2, 'ROLE_ADMIN');
insert into public."users" ("id", "name", "username", "password", "lastpasswordresetdate", "email", "dateofbirth",
                            "age")
values (30, 'test', 'test', '$2a$10$11mQXna4JSy.WLF9tLGfsuwCuidS3jQ80Fp.ugPIavultQ9yCIwym',
        current_date - INTERVAL '1 DAY', 'test@test.com', current_date, 20);
insert into public."roles_users" (users, roles)
values (30, 1);
insert into public."roles_users" (users, roles)
values (30, 2);

INSERT INTO activity (id, location, name, startdate)
VALUES (1, 'New York', 'Hiking in Central Park', '2023-09-18'),
       (2, 'San Francisco', 'Golden Gate Bridge Tour', '2023-09-19'),
       (3, 'London', 'British Museum Visit', '2023-09-20'),
       (4, 'Paris', 'Louvre Museum Tour', '2023-09-21'),
       (5, 'Tokyo', 'Sushi Making Class', '2023-09-22'),
       (6, 'Sydney', 'Opera House Tour', '2023-09-23'),
       (7, 'Rome', 'Colosseum Exploration', '2023-09-24'),
       (8, 'Barcelona', 'Sagrada Familia Visit', '2023-09-25'),
       (9, 'Berlin', 'Brandenburg Gate Tour', '2023-09-26'),
       (10, 'Amsterdam', 'Canal Cruise', '2023-09-27'),
       (11, 'Dubai', 'Burj Khalifa Viewing', '2023-09-28'),
       (12, 'Rio de Janeiro', 'Christ the Redeemer Visit', '2023-09-29'),
       (13, 'Cape Town', 'Table Mountain Hike', '2023-09-30'),
       (14, 'Bangkok', 'Grand Palace Exploration', '2023-10-01'),
       (15, 'New Delhi', 'Taj Mahal Tour', '2023-10-02'),
       (16, 'Beijing', 'Great Wall Adventure', '2023-10-03'),
       (17, 'Moscow', 'Red Square Visit', '2023-10-04'),
       (18, 'New Orleans', 'French Quarter Experience', '2023-10-05'),
       (19, 'Toronto', 'CN Tower Viewing', '2023-10-06'),
       (20, 'Buenos Aires', 'Tango Dance Lesson', '2023-10-07');
