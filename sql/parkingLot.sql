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
	parking_num varchar(10) not null, /*停车场编号*/
	parking_name varchar(50) not null, /*名字*/
	address varchar(100) not null,  /*地址*/
	total int(8) not null,  /*容量*/
	inuse int(8) not null, /*已使用*/
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
	primary key(id)
);





