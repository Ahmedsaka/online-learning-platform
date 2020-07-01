CREATE TABLE Role(
  id BIGINT auto_increment primary key ,
  name VARCHAR (50) UNIQUE,
  description VARCHAR (255)
);

CREATE TABLE User(
  id BIGINT auto_increment primary key ,
  username VARCHAR (50) not null ,
  email VARCHAR (50) NOT NULL ,
  password VARCHAR (100) NOT NULL
);

CREATE TABLE Students(
  id BIGINT auto_increment primary key ,
  first_name VARCHAR (50) not null ,
  last_name VARCHAR (50) NOT NULL ,
  username VARCHAR (50) NOT NULL ,
  email VARCHAR (50) NOT NULL ,
  password VARCHAR (100) NOT NULL
);

CREATE TABLE Instructors(
  id BIGINT auto_increment primary key ,
  username VARCHAR (50) NOT NULL ,
  email VARCHAR (50) NOT NULL ,
  password VARCHAR (100) NOT NULL
);

CREATE TABLE Courses(
  id BIGINT auto_increment primary key ,
  course_name VARCHAR (50) not null ,
  description VARCHAR (50) NOT NULL ,
  instructor_name VARCHAR (50) NOT NULL
);

CREATE TABLE role_user(
  id BIGINT auto_increment primary key ,
  role_id BIGINT REFERENCES Role(id),
  user_id BIGINT REFERENCES User(id)
);

CREATE TABLE student_course(
  id BIGINT auto_increment primary key ,
  course_id BIGINT REFERENCES Courses(id),
  student_id BIGINT REFERENCES Students(id)
);

--CREATE IF NOT EXISTS TABLE Permissions(
--id BIGINT auto_increment primary key ,
--name VARCHAR (50) UNIQUE,
--description VARCHAR (255)
--);

--CREATE IF NOT EXISTS TABLE Permissions(
--id BIGINT auto_increment primary key ,
--role_id BIGINT FOREIGN KEY REFERENCES Roles(id),
--permission_id BIGINT FOREIGN KEY REFERENCES Permissions(id)
--);