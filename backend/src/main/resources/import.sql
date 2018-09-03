insert into public."roles" ("id", "name") values (1, 'ROLE_STUDENT');
insert into public."roles" ("id", "name") values (2, 'ROLE_ADMIN');
insert into public."users" ("id", "name", "username", "password", "email", "date_of_birth", "age") values (30, 'test', 'test', 'test', 'test@test.com', current_date, 20);
insert into public."role_users" (users, roles) values (30, 1);
insert into public."role_users" (users, roles) values (30, 2);