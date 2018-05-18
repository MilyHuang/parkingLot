DROP TABLE IF EXISTS admin;

CREATE TABLE admin(
   id int(8) auto_increment comment 'systemId',
   username varchar(8) not null,
   password varchar(500) not null,
   role int(2) not null comment '0 admin , 1 manager ,2 operator',
   primary key (id)
);




