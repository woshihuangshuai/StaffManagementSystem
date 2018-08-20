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