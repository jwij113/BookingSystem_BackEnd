insert into system_user (id, first_name, last_name, password, email) values (9999, 'Joko', 'Wijaya', 'jokojoko', 'jokowi@gmail.com');
insert into system_user (id, first_name, last_name, password, email) values (99999, 'Fred', 'Blog', 'jokojoko', 'fred@gmail.com');
insert into system_user (id, first_name, last_name, password, email) values (999999, 'Peter', 'Pitrelli', 'jokojoko', 'peter@gmail.com');

insert into role values (1, 'user');
insert into role values (2, 'officer');
insert into role values (3, 'admin');

insert into user_role values (9999, 1);
insert into user_role values (9999, 2);
insert into user_role values (9999, 3);

insert into user_role values (99999, 1);
insert into user_role values (99999, 2);

insert into user_role values (999999, 1);
