create database facturador;

create table cliente
id_cliente varchar(200) auto_increment primary key,
nombre varchar(200) not null,
apellido varchar(200) not null,
dni integer not null

create table producto
id_producto varchar(200) auto_increment primary key,
marca varchar(200),
serial_number varchar(200),
cantidad integer unsigned ,
precio integer unsigned

create table recibo
idrecibo varchar (200) auto_increment primary key not null,
fecha date time,
total float (5,2),
cantidad integer,
cliente_id integer foreign key ,
producto_id integer foreign key