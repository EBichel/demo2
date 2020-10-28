create table subscriptions (
    id uuid primary key,
    product_id uuid not null,
    start_date timestamp not null,
    end_date timestamp,
    active boolean not null,
    paused boolean not null
);
