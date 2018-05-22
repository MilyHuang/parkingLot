drop table if exists parkingLot_price;

create table parkingLot_price(
    id int(8) auto_increment,
    month int(8) not null,
    price float(6,2) default 0 not null,
    p_id int(8) not null,
    primary key(id),
    constraint price_parkinglot foreign key(p_id) references parking_lot(id)
);