insert into course(id, name, created_date, last_updated_date, is_deleted) values (101, 'Introduction to Astrophysics', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted) values (102, 'Introduction to Spring', sysdate(), sysdate(), false);
insert into course(id, name, created_date, last_updated_date, is_deleted) values (103, 'Introduction to Spring Boot', sysdate(), sysdate(), false);

insert into passport(id, number) values(4001, 'E123456');
insert into passport(id, number) values(4002, 'N123456');
insert into passport(id, number) values(4003, 'I123456');

insert into student(id, name, passport_id) values(2001, 'Tony', 4001);
insert into student(id, name, passport_id) values(2002, 'Cap', 4002);
insert into student(id, name, passport_id) values(2003, 'Nat', 4003);

insert into review(id, rating, description, course_id) values(5001, 5, 'Great Course!', 101);
insert into review(id, rating, description, course_id) values(5002, 4, 'Wonderful Course!', 101);
insert into review(id, rating, description, course_id) values(5003, 5, 'Awesome Course!', 103);

insert into student_course(student_id, course_id) values (2001L,101L);
insert into student_course(student_id, course_id) values (2002L,101L);
insert into student_course(student_id, course_id) values (2003L,101L);
insert into student_course(student_id, course_id) values (2001L,103L);