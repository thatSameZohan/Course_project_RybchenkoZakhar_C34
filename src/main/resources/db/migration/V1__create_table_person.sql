create table persons (
    id uuid primary key,
    username varchar,
    password varchar,
    authority varchar,
    is_enabled boolean
);