#show databases;
drop database if exists shop;
create database shop;
use  shop;
SET SQL_SAFE_UPDATES=0;
SET FOREIGN_KEY_CHECKS=1;

create table product(
	serial_num int NOT NULL,
    product_name varchar(24),
    price Real,
    stock int ,
    category varchar(10),
    cost Real,
    supplier_name VARCHAR(70),
    PRIMARY KEY (serial_num )	
);

create table Customers(
	C_ID int, 
	C_Name varchar(32),
	C_Address varchar (32), 
	primary key(C_ID)
);


CREATE TABLE C_Phone(
	C_ID int,
	Phone varchar (32) NOT NULL,
	PRIMARY KEY (Phone,C_ID),
	Foreign Key (C_ID) REFERENCES Customers(C_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

create table orders(
	orderId int, productId int,
	customerId int, 
	quantity int,
	orderDate Date ,
	orderStatus varchar (10),
	primary key(orderId, productId),
	foreign key (productId) references product (serial_num)  ON DELETE CASCADE ON UPDATE CASCADE,
	foreign key (customerId) references Customers (C_ID)  ON DELETE CASCADE ON UPDATE CASCADE,
	INDEX `orderId` (`orderId`),
	check (orderStatus in ("yes", "no"))
);



CREATE TABLE Payment(
	Pay_ID int,
	O_ID int, 
	Pay_amount int, 
	Pay_Date Date, 
	PRIMARY KEY (Pay_ID),
	CONSTRAINT `FK_O_ID`
	foreign Key (O_ID) REFERENCES orders(orderId) ON DELETE CASCADE ON UPDATE CASCADE
);


create table supplier(
	supplierID int,
	supplier_Name varchar(24),
	address varchar(10),
	mobile_num varchar(10),
	email varchar(40),
	primary key (supplierID)
);


create table shipment(
	shipmentId int, 
	productId int, 
	quantity int, 
	requestDate Date, 
	arrivalDate Date, 
	shipmentStatus varchar(5), 
	primary key(shipmentId, productId)
);
					 

  

 

select * from Customers;
select * from C_Phone;
select * from orders;
select * from Payment;
select * from product;
select * from shipment;
select * from supplier;

SELECT * FROM product;

 select product.serial_num, product.product_name, sum(orders.quantity) as amount from orders , product  where product.serial_num=orders.productId group by product.serial_num  order by amount desc
 