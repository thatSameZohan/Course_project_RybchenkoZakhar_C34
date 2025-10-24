create table lessons(
    id uuid primary key,
    number int,
    name varchar,
    course_id uuid references courses
);