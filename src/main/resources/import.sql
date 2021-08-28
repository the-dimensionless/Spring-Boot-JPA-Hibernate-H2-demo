insert into course(id, name, created_date, last_updated_date) values (101, 'Introduction to Astrophysics', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values (102, 'Introduction to Spring', sysdate(), sysdate());
insert into course(id, name, created_date, last_updated_date) values (103, 'Introduction to Spring Boot', sysdate(), sysdate());

insert into passport(id, number) values(4001, 'E123456');
insert into passport(id, number) values(4002, 'N123456');
insert into passport(id, number) values(4003, 'I123456');

insert into student(id, name, passport_id) values(2001, 'Tony', 4001);
insert into student(id, name, passport_id) values(2002, 'Cap', 4002);
insert into student(id, name, passport_id) values(2003, 'Nat', 4003);

insert into review(id, rating, description) values(5001, '5', 'Great Course!');
insert into review(id, rating, description) values(5002, '4', 'Wonderful Course!');
insert into review(id, rating, description) values(5003, '5', 'Awesome Course!');
