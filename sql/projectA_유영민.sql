create database projectA;

use projectA;

drop table if exists customer;

create table customer (
	cno int not null comment '고객 번호', 
	cname varchar(3) comment '고객명',
	cmoney int default 0 null comment '적립금',
	mno int null comment '멤버십 번호',
    CONSTRAINT pk_customer PRIMARY KEY ( cno )
) comment '고객';

create table membership (
	mno int not null comment '멤버십 번호',
	mname varchar(10) comment '멤버십 등급',
    CONSTRAINT pk_membership PRIMARY KEY ( mno )
) comment '멤버십'

select * from information_schema.TABLE_CONSTRAINTS tc 
where TABLE_NAME = 'customer';

ALTER TABLE customer 
	ADD CONSTRAINT fk_cust_mbrs FOREIGN KEY ( mno ) REFERENCES membership( mno ) 
	ON DELETE NO ACTION ON UPDATE NO ACTION;

desc customer;

insert into membership value(10, 'silver');
insert into membership value(20, 'gold');
insert into membership value(30, 'diamond');

insert into customer value(101, 'NOA', 100, 10);
insert into customer value(102, 'MOA', 800, 20);
insert into customer value(103, 'KAL', 600, null);

select * from customer c ;
select * from membership m ;