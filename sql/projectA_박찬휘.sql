create database projectA;

use projectA;

create table customer(
	cno int not null,
	cname varchar(3),
	cmoney int,
	mno int,
	constraint pk_cno primary key (cno)
)

create table membership(
	mno int not null,
	mname varchar(10),
	constraint pk_mno primary key (mno)
)

ALTER TABLE customer 
ADD CONSTRAINT fk_cust_mbrs FOREIGN KEY ( mno ) REFERENCES membership( mno )
ON DELETE NO ACTION ON UPDATE NO ACTION;

select * from information_schema.table_constraints where TABLE_NAME='customer';

insert into membership value(10, 'silver');
insert into membership value(20, 'gold');
insert into membership value(30, 'diamond');

desc customer;
desc membership;

select * from membership;

insert into customer value(101, 'NOA', 100, 10);
insert into customer value(102, 'MOA', 800, 20);
insert into customer value(103, 'KAL', 600, null);

select * from customer;

update customer set cmoney=800 WHERE cno=102;

delete from customer where cno>106;
