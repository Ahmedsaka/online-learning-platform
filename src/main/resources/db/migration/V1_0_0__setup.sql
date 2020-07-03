
CREATE TABLE Role(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (50) UNIQUE,
  description VARCHAR (255)
);

CREATE TABLE User(
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  first_name VARCHAR NOT NULL ,
  last_name VARCHAR NOT NULL ,
  username VARCHAR (50) NOT NULL ,
  email VARCHAR (50) UNIQUE ,
  password VARCHAR (50) NOT NULL ,
  role_id INT REFERENCES Role(id)
);

CREATE TABLE role_user(
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  role_id BIGINT REFERENCES role(id),
  user_id BIGINT REFERENCES user(id)
);

CREATE TABLE Course(
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  course_name VARCHAR (100) NOT NULL ,
  instructor_name VARCHAR (50) NOT NULL ,
  description VARCHAR (255)
);

CREATE TABLE Student(
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  first_name VARCHAR (50) NOT NULL ,
  last_name VARCHAR (50) NOT NULL ,
  email VARCHAR (50) UNIQUE ,
  password VARCHAR (50) NOT NULL ,
  course_id BIGINT REFERENCES Course(id)
);

CREATE TABLE course_student(
  id BIGINT AUTO_INCREMENT PRIMARY KEY ,
  course_id BIGINT REFERENCES Course(id),
  student_id BIGINT REFERENCES Student(id)
);