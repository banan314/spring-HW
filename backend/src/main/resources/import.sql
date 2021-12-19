insert into public."roles" ("id", "name") values (1, 'ROLE_STUDENT');
insert into public."roles" ("id", "name") values (2, 'ROLE_ADMIN');
insert into public."users" ("id", "name", "username", "password", "lastpasswordresetdate", "email", "date_of_birth", "age") values (30, 'test', 'test', '$2a$10$11mQXna4JSy.WLF9tLGfsuwCuidS3jQ80Fp.ugPIavultQ9yCIwym', current_date - INTERVAL '1 DAY', 'test@test.com', current_date, 20);
insert into public."roles_users" (users, roles) values (30, 1);
insert into public."roles_users" (users, roles) values (30, 2);