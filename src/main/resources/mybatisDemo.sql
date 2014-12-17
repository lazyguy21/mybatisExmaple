/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014-12-17 14:20:15                          */
/*==============================================================*/


drop table if exists address;

drop table if exists course;

drop table if exists course_enrollment;

drop table if exists student;

drop table if exists tutor;

/*==============================================================*/
/* Table: address                                               */
/*==============================================================*/
create table address
(
   id                   unsigned integer not null,
   street               varchar(50),
   city                 varchar(50),
   country              varchar(50),
   state                varchar(50),
   zip                  varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   name                 varchar(20),
   description          varchar(20),
   start_date           date,
   end_date             date,
   tutor_id             integer unsigned,
   id                   integer unsigned not null,
   primary key (id)
);

/*==============================================================*/
/* Table: course_enrollment                                     */
/*==============================================================*/
create table course_enrollment
(
   course_id            integer not null,
   stu_id               unsigned integer not null,
   primary key (course_id, stu_id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   id                   unsigned integer not null,
   name                 varchar(20),
   email                varchar(50),
   phone                varchar(30),
   dob                  varchar(20),
   addr_id              unsigned integer,
   primary key (id)
);

/*==============================================================*/
/* Table: tutor                                                 */
/*==============================================================*/
create table tutor
(
   id                   integer,
   name                 varbinary(20),
   email                varbinary(50),
   phone                varbinary(30),
   addr_id              integer
);

alter table course add constraint FK_aaa foreign key (tutor_id)
      references tutor (id) on delete restrict on update restrict;

alter table course_enrollment add constraint FK_Reference_4 foreign key (course_id)
      references course (id) on delete restrict on update restrict;

alter table course_enrollment add constraint FK_Reference_5 foreign key (stu_id)
      references student (id) on delete restrict on update restrict;

alter table student add constraint FK_Reference_3 foreign key (addr_id)
      references address (id) on delete restrict on update restrict;

alter table tutor add constraint FK_Reference_1 foreign key (addr_id)
      references address (id) on delete restrict on update restrict;

