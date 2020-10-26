create table books (
    id uuid primary key,
    author varchar(255) not null,
    title varchar(255) not null,
    borrowed boolean not null
);