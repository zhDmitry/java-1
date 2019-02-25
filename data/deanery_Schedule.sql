INSERT INTO deanery123.Schedule (id, Day, LessonOrder) VALUES (1, 'Monday', 1);
INSERT INTO deanery123.Schedule (id, Day, LessonOrder) VALUES (2, 'Tuesday', 2);
INSERT INTO deanery123.Schedule (id, Day, LessonOrder) VALUES (3, 'Friday', 1);


INSERT INTO deanery123.Semesters (id, year, semester) VALUES (1, 2018, '1');
INSERT INTO deanery123.Semesters (id, year, semester) VALUES (2, 2019, '2');
INSERT INTO deanery123.Semesters (id, year, semester) VALUES (3, 2019, '3');

INSERT INTO deanery123.Lecturers (id, name, position, cathedra_id) VALUES (1, 'Petrenko', 'Lecturer', 1);
INSERT INTO deanery123.Lecturers (id, name, position, cathedra_id) VALUES (2, 'Homenko', 'Sr. Lecturer', 1);

INSERT INTO deanery123.Courses (id, name, number_of_lectures, number_of_practices, summarize, is_norrmative, number_of_credits, cathedra_id) VALUES (1, 'Java', 6, 12, 'Learn Java language basics', 1, 4, 1);
INSERT INTO deanery123.Courses (id, name, number_of_lectures, number_of_practices, summarize, is_norrmative, number_of_credits, cathedra_id) VALUES (2, 'C#', 6, 12, 'Learn C# basics', 1, 4, 1);


INSERT INTO deanery123.Cathedras (id, name, hall) VALUES (1, 'Informatics', 'First building');

INSERT INTO deanery123.Auditory (id, num, hall) VALUES (1, 1, 'Second building');