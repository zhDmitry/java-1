CREATE DATABASE IF NOT EXISTS deanery123;
USE deanery123;


create table if not exists Auditory
(
  id            int   auto_increment
    primary key,
  num           int          null,
  building_name varchar(200) null
);

create table if not exists Cathedras
(
  id   int auto_increment,
  name int         not null,
  hall varchar(20) not null,
  constraint id_UNIQUE
    unique (id)
);

alter table Cathedras
  add primary key (id);

create table if not exists Courses
(
  id                  int auto_increment,
  name                varchar(100) not null,
  number_of_lectures  int          not null,
  number_of_practices int          not null,
  summarize           varchar(45)  not null,
  is_norrmative       tinyint      not null,
  number_of_credits   int          not null,
  cathedra_id         int          not null,
  primary key (id, cathedra_id),
  constraint id_UNIQUE
    unique (id),
  constraint fk_Courses_Cathedras
    foreign key (cathedra_id) references Cathedras (id)
);

create table if not exists Course_has_prerequisite_Courses
(
  course_id                       int not null,
  course_cathedra_id              int not null,
  prerequisite_course_id          int not null,
  prerequisite_course_cathedra_id int not null,
  primary key (course_id, course_cathedra_id, prerequisite_course_id, prerequisite_course_cathedra_id),
  constraint fk_Courses_has_Courses_Courses1
    foreign key (course_id, course_cathedra_id) references Courses (id, cathedra_id),
  constraint fk_Courses_has_Courses_Courses2
    foreign key (prerequisite_course_id, prerequisite_course_cathedra_id) references Courses (id, cathedra_id)
);

create index fk_Courses_has_Courses_Courses1_idx
  on Course_has_prerequisite_Courses (course_id, course_cathedra_id);

create index fk_Courses_has_Courses_Courses2_idx
  on Course_has_prerequisite_Courses (prerequisite_course_id, prerequisite_course_cathedra_id);

create index fk_Courses_Cathedras_idx
  on Courses (cathedra_id);

create table if not exists Lecturers
(
  id          int auto_increment,
  name        varchar(60) not null,
  position    varchar(45) not null,
  cathedra_id int         not null,
  primary key (id, cathedra_id),
  constraint id_UNIQUE
    unique (id),
  constraint fk_Lecturers_Cathedras1
    foreign key (cathedra_id) references Cathedras (id)
);

create index fk_Lecturers_Cathedras1_idx
  on Lecturers (cathedra_id);

create table if not exists Schedule
(
  id          int auto_increment,
  Day         varchar(10) not null,
  LessonOrder int(2)      null,
  constraint Term_id_uindex
    unique (id)
);

alter table Schedule
  add primary key (id);

create table if not exists Semesters
(
  id       int auto_increment,
  year     int         null,
  semester varchar(20) null,
  constraint id_UNIQUE
    unique (id)
);

alter table Semesters
  add primary key (id);

create table if not exists Lessons
(
  id                   int auto_increment,
  type                 varchar(50) null,
  lecturer_id          int         not null,
  lecturer_cathedra_id int         not null,
  semester_id          int         not null,
  course_id            int         not null,
  course_cathedra_id   int         not null,
  schedule_id          int         null,
  primary key (id, lecturer_id, lecturer_cathedra_id, semester_id, course_id, course_cathedra_id),
  constraint id_UNIQUE
    unique (id),
  constraint Lessons_Schedule_id_fk
    foreign key (schedule_id) references Schedule (id),
  constraint fk_Lessons_Courses1
    foreign key (course_id, course_cathedra_id) references Courses (id, cathedra_id),
  constraint fk_Lessons_Lecturers1
    foreign key (lecturer_id, lecturer_cathedra_id) references Lecturers (id, cathedra_id),
  constraint fk_Lessons_Semester1
    foreign key (semester_id) references Semesters (id)
);

create table if not exists AuditoryToLessons
(
  auditory_id int not null,
  lesson_id   int not null,
  primary key (lesson_id, auditory_id),
  constraint AuditoryToLessons_Auditory_id_fk
    foreign key (auditory_id) references Auditory (id)
      on update cascade on delete cascade,
  constraint AuditoryToLessons_Lessons_id_fk
    foreign key (lesson_id) references Lessons (id)
      on update cascade on delete cascade
);

create index fk_Lessons_Courses1_idx
  on Lessons (course_id, course_cathedra_id);

create index fk_Lessons_Lecturers1_idx
  on Lessons (lecturer_id, lecturer_cathedra_id);

create index fk_Lessons_Semester1_idx
  on Lessons (semester_id);

create table if not exists Stream
(
  Name varchar(100) null,
  id   int          not null
    primary key,
  constraint Stream_Lessons_id_fk
    foreign key (id) references Lessons (id)
      on update cascade on delete cascade
);

create table if not exists `Group`
(
  id        int      not null
    primary key,
  group_no  int(100) null,
  stream_id int      null,
  constraint Group_Lessons_id_fk
    foreign key (id) references Lessons (id)
      on update cascade on delete cascade,
  constraint Group_Stream_stream_id_fk
    foreign key (stream_id) references Stream (id)
);

