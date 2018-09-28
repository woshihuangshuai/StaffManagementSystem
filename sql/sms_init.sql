create database sms;

use sms;
create table staff_info (
staff_id int unsigned auto_increment,
staff_name varchar(40) not null,
staff_date_of_birth date,
staff_department varchar(40),
staff_post varchar(40),
staff_level varchar(40),
primary key (staff_id)
);

use sms;
insert into staff_info values (
null, 
'黄帅', 
'1993-04-10', 
'对外业务拓展中心',
'市场拓展团队', 
'Level 1'
);

insert into staff_info values (
null, 
'高喜龙', 
'1994-04-10', 
'对外业务拓展中心',
'市场拓展团队', 
'Level 2'
);

insert into staff_info values (
null, 
'贾华杰', 
'1992-04-10', 
'对外业务拓展中心',
'市场拓展团队', 
'Level 5'
);

insert into staff_info values (
null, 
'高松', 
'1983-04-10', 
'对外业务拓展中心',
'市场拓展团队', 
'Level 10'
);

/*显示当前数据库时间*/
select NOW();
/*查看当前数据库时区*/
show variables like "%time_zone%";
/*设置全局数据库时区*/
set global time_zone = '+8:00';
/*设置当前会话时区*/
set time_zone = '+8:00';
/*立即生效*/
flush privileges;

CREATE TABLE t_attendance_record (
	record_id INT ( 12 ) NOT NULL COMMENT '考勤记录ID' auto_increment,
	staff_id INT ( 12 ) NOT NULL COMMENT '员工ID',
	date TIMESTAMP DEFAULT now( ) NOT NULL COMMENT '考勤时间',
	PRIMARY KEY ( record_id ) 
);








