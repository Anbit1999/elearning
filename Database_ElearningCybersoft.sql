use elearning;

insert into roles(name,description) values ('ROLE_ADMIN', 'Quản trị hệ thống');
insert into roles(name,description) values ('ROLE_TEACHER', 'Giảng viên');
insert into roles(name,description) values ('ROLE_STUDENT', 'Học Viên');


insert into categories(title,icon) values ('Python', 'python.ico');
insert into categories(title,icon) values ('Java', 'java.ico');
insert into categories(title,icon) values ('PHP', 'php.ico');
insert into categories(title,icon) values ('Javascript', 'javascript.ico');

insert into courses(content,description,discount,hour_count,image,last_update,lectures_count,price,promotion_price,title,
view_count,category_id) values ('Đào tạo chuyên về lĩnh vực trí tuệ nhân tạo', "Học viên sẽ được đào tạo trong 6 tháng, quá trình thực hiện
vừa học vừa thực hành đảm bảo chất lượng đầu ra. Với sự hỗ trợ từ giảng viên và mentor sau 6 tháng học viên sẽ có thể viết được 1 ứng dụng
nhận diện khuôn mặt, phân tích dữ liệu",0.5,90,'AI.png',now(),75,5000000,4000000,'Lập trình AI',5000000,1);

insert into courses(content,description,discount,hour_count,image,last_update,lectures_count,price,promotion_price,title,
view_count,category_id) values ('Đào tạo chuyên về lĩnh vực lập trình web', "Học viên sẽ được đào tạo trong 4 tháng, quá trình thực hiện
vừa học vừa thực hành đảm bảo chất lượng đầu ra. Với sự hỗ trợ từ giảng viên và mentor sau 4 tháng học viên sẽ có thể viết được 1 trang
web sử dụng framework java springboot",0.8,75,'java.png',now(),60,9000000,8000000,'Lập trình Web backend Java',1000000000,2);

insert into targets(title,course_id) values ('Viết 1 ứng dụng nhận diện khuôn mặt và phân tích dữ liệu',1);
insert into targets(title,course_id) values ('Viết 1 trang web sử dụng spring boot',2);

insert into videos(time_count,title,url,course_id) values (200,'Introduction Python','https://www.udemy.com/course/python-tutorial-for-beginners/?utm_source=adwords&utm_medium
',1);
insert into videos(time_count,title,url,course_id) values (150,'Getting Started with Programing','https://www.udemy.com/course/python-tutorial-for-beginners/?utm_source=adwords&utm_medi
',1);
insert into videos(time_count,title,url,course_id) values (250,'Python Expression','https://www.udemy.com/course/python-tutorial-for-beginners/?utm_source=adwords&utm_medium=udemyads&utm_ca
',1);
insert into videos(time_count,title,url,course_id) values (250,'Java Servlet','https://www.udemy.com/course/java-programming-tutorial-for-beginners/?utm_source=adwords&utm_medium
',2);
insert into videos(time_count,title,url,course_id) values (200,'Java Spring MVC','https://www.udemy.com/course/java-programming-tutorial-for-beginners/?utm_source=adwords&utm_medium
',2);
insert into videos(time_count,title,url,course_id) values (250,'Java Spring boot','https://www.udemy.com/course/java-programming-tutorial-for-beginners/?utm_source=adwords&utm_medium
',2);

insert into user_courses(user_id, course_id) values(3,1);
insert into user_courses(user_id, course_id) values(3,2);
