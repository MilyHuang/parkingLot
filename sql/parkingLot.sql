/**用户管理表*/
DROP TABLE IF EXISTS admin;

CREATE TABLE admin(
   id int(8) auto_increment comment 'systemId',
   username varchar(8) not null,
   password varchar(500) not null,
   role int(2) not null comment '0 admin , 1 manager ,2 operator',
   primary key (id)
);

/*停车场表*/
DROP TABLE IF EXISTS parking_lot;

CREATE TABLE parking_lot(
	id int(8) auto_increment comment 'systemId',
	parking_num varchar(10) not null unique, /*停车场编号*/
	parking_name varchar(50) not null, /*名字*/
	address varchar(100) not null,  /*地址*/
	total int(8) not null,  /*容量*/
	inuse int(8) not null, /*已使用*/
	price double not null, /*停车场价格*/
	rent double not null, /*租金*/
	primary key(id)
);


/**用户办卡信息表*/
DROP TABLE IF EXISTS users_info;

CREATE TABLE users_info(
	id int(8) auto_increment comment 'systemId',
	username varchar(10) not null,  /**用户名*/
	phone varchar(11) not null, /*手机号码*/
	password varchar(500) not null, /*登录密码*/
	primary key(id)
);

/**停车卡信息表*/
DROP TABLE IF EXISTS parking_card;

CREATE TABLE parking_card(
	id int(8) auto_increment comment 'systemId',
	users_id int(8) not null comment 'users id',  /*关联users_info表的ID*/
	parking_num varchar(10) not null, /**停车场编号*/
	card_num varchar(20) not null,  /*停车卡号*/
	createdTime datetime not null, /*办卡时间*/
	state int(2) not null, /*状态：0 可用 ，1 不可用*/
	primary key(id)
);


/**修改价格记录表*/
DROP TABLE IF EXISTS parkingPrice_report;

CREATE TABLE parkingPrice_report(
	id int(8) auto_increment comment 'systemId',
	parkingNum varchar(20) not null,            /*关联的parkint_lot表id*/
	price double not null,            /*修改的价格*/
	datetime varchar(20) not null,        /*修改的时间*/
	primary key(id)
);


/**用户停车取车记录表*/
DROP TABLE IF EXISTS parking_record;

CREATE TABLE parking_record(
	id int(8) auto_increment comment 'systemId',
	phone varchar(11) not null , /**用户手机号*/
	parking_num varchar(10) not null, /**停车场编号*/
	card_num varchar(20) not null, /**停车卡号*/
	checkin_time datetime ,/*停车时间*/
	checkout_time datetime, /**取车时间*/
	flag int(2) , /*停车或取车标志 0 停车，1 取车*/
	primary key (id)
);
alter table parking_record add constraint flag_check check(flag in(0,1));










