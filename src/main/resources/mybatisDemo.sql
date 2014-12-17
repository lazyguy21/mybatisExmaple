/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-12-17 15:13:02                          */
/*==============================================================*/


DROP TABLE IF EXISTS address;

DROP TABLE IF EXISTS course;

DROP TABLE IF EXISTS course_enrollment;

DROP TABLE IF EXISTS student;

DROP TABLE IF EXISTS tutor;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
CREATE TABLE address
(
  id      INTEGER UNSIGNED NOT NULL,
  street  VARCHAR(50),
  city    VARCHAR(50),
  country VARCHAR(50),
  state   VARCHAR(50),
  zip     VARCHAR(50),
  PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
CREATE TABLE course
(
  NAME        VARCHAR(20),
  description VARCHAR(20),
  start_date  DATE,
  end_date    DATE,
  tutor_id    INTEGER UNSIGNED,
  id          INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: course_enrollment                                     */
/*==============================================================*/
CREATE TABLE course_enrollment
(
  course_id INTEGER UNSIGNED NOT NULL,
  stu_id    INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY (course_id, stu_id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
CREATE TABLE student
(
  id      INTEGER UNSIGNED NOT NULL,
  NAME    VARCHAR(20),
  email   VARCHAR(50),
  phone   VARCHAR(30),
  dob     VARCHAR(20),
  addr_id INTEGER UNSIGNED,
  PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: tutor                                                 */
/*==============================================================*/
CREATE TABLE tutor
(
  id      INTEGER UNSIGNED NOT NULL,
  NAME    VARBINARY(20),
  email   VARBINARY(50),
  phone   VARBINARY(30),
  addr_id INTEGER UNSIGNED,
  PRIMARY KEY (id)
);

ALTER TABLE course ADD CONSTRAINT FK_aaa FOREIGN KEY (tutor_id)
REFERENCES tutor (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE course_enrollment ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (course_id)
REFERENCES course (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE course_enrollment ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (stu_id)
REFERENCES student (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE student ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (addr_id)
REFERENCES address (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE tutor ADD CONSTRAINT FK_Reference_1 FOREIGN KEY (addr_id)
REFERENCES address (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

