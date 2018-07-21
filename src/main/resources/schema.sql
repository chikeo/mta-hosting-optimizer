drop table if exists ipconfig;

create table ipconfig

(

   ip varchar(255) not null,

   hostfqdn varchar(255) not null,

   active char(1) not null,

   primary key(ip)

);