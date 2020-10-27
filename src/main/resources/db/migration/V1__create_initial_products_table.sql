create table products (
    id uuid primary key,
    name varchar(255) not null,
    duration bigint not null,
    net_price bigint not null,
    tax_rate bigint not null
);

--- insert product mock data
insert into products values
	('15993ed6-d4be-4ebc-b235-18b3cf1ccf6a', 'cheap subscription', 3600, 9999, 1600),
	('b41062aa-4c5c-4d8e-a54f-c4a6ef072181', 'expensive subscription', 3600, 19999, 1600);
